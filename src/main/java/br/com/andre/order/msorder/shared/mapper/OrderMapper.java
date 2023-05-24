package br.com.andre.order.msorder.shared.mapper;

import br.com.andre.order.msorder.api.dto.OrderDto;
import br.com.andre.order.msorder.api.dto.ItemDto;
import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.integration.broker.dto.OrderBrokerDto;

import java.util.List;

public interface OrderMapper {

    List<OrderDto> ordersToOrderDtoList(List<Order> orders);

    List<ItemDto> orderItemsToItemDto(Order order);

    Order orderBrokerDtoToOrderEntity(OrderBrokerDto dto);

}
