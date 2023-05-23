resource "aws_vpc" "ms_order_vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    "Name" = "ms_order_vpc"
  }

}

resource "aws_subnet" "ms_order_subnet_public_1a" {
  vpc_id                  = aws_vpc.ms_order_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "ms_order_subnet_public_1a"
  }

}

resource "aws_internet_gateway" "ms_order_igw_1a" {
  vpc_id = aws_vpc.ms_order_vpc.id

  tags = {
    Name = "ms_order_igw_1a"
  }

}

resource "aws_route_table" "ms_order_rtb_public" {
  vpc_id = aws_vpc.ms_order_vpc.id

  tags = {
    Name = "ms_order_rtb_public"
  }

}

resource "aws_route" "ms_order_route" {
  route_table_id         = aws_route_table.ms_order_rtb_public.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.ms_order_igw_1a.id
}

resource "aws_route_table_association" "ms_order_rtb_ass" {
  route_table_id = aws_route_table.ms_order_rtb_public.id
  subnet_id      = aws_subnet.ms_order_subnet_public_1a.id
}

resource "aws_instance" "ms_order_ec2_inst" {
  instance_type          = "t2.small"
  key_name               = aws_key_pair.ms_order_key.id
  vpc_security_group_ids = [aws_security_group.ms_order_sg.id]
  subnet_id              = aws_subnet.ms_order_subnet_public_1a.id
  user_data              = file("userdata.tpl")

  ami = data.aws_ami.ms_order_ami.id

  root_block_device {
    volume_size = 8
  }

  tags = {
    Name = "ms_order_ec2_inst"
  }

}

resource "aws_db_instance" "ms_order_db" {
  instance_class = "db.t2.micro"
  allocated_storage = 5
  max_allocated_storage = 10
  engine = "aurora-postgresql"
  engine_version = "13"
  db_name = "msorder"
  username = "admin"
  password = "admin"
  skip_final_snapshot = true
}

resource "aws_db_subnet_group" "ms_order_subnet_gp" {
  name = "ms_order_subnet_gp"
  subnet_ids = [aws_subnet.ms_order_subnet_public_1a.id]
}