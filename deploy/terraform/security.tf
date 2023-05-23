resource "aws_security_group" "ms_order_sg" {
  name        = "ms_order_sg"
  description = "MS Order Security Group"
  vpc_id      = aws_vpc.ms_order_vpc.id

  tags = {
    "Name" = "ms_order_sg"
  }

}

resource "aws_security_group_rule" "ms_order_sgr_out_public" {
  from_port         = 0
  to_port           = 0
  protocol          = "-1"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "egress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_http_in" {
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_https_in" {
  from_port         = 443
  to_port           = 443
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_db_in" {
  from_port         = 5432
  to_port           = 5432
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_broker_in" {
  from_port         = 5672
  to_port           = 5672
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_broker_management_in" {
  from_port         = 15672
  to_port           = 15672
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "ms_order_sgr_ssh_in" {
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  security_group_id = aws_security_group.ms_order_sg.id
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_key_pair" "ms_order_key" {
  key_name   = "ms_order_key"
  public_key = file("~/.ssh/ms_order_key.pub")
}