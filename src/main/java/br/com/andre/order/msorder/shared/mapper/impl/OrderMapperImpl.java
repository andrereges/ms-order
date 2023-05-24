package br.com.andre.order.msorder.shared.mapper.impl;

import br.com.andre.order.msorder.api.dto.ItemDto;
import br.com.andre.order.msorder.api.dto.OrderDto;
import br.com.andre.order.msorder.domain.entity.Item;
import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.integration.broker.dto.OrderBrokerDto;
import br.com.andre.order.msorder.shared.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    @Override
    public List<OrderDto> ordersToOrderDtoList(List<Order> orders) {
        return orders.stream().map(
                order ->
                    new OrderDto(
                            order.getOrderId(),
                            order.getQuantityItems(),
                            order.getTotalOrder(),
                            this.orderItemsToItemDto(order)
                    )
        ).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> orderItemsToItemDto(Order order) {
        return order.getItems().stream()
                .map(i -> new ItemDto(i.getName(), i.getQuantity(), i.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Order orderBrokerDtoToOrderEntity(OrderBrokerDto dto) {
        Order order = Order.builder()
                .orderId(dto.orderId())
                .customerId(dto.customerId())
                .build();

        List<Item> items = dto.items().stream()
                .map(item ->
                        Item.builder()
                                .name(item.name())
                                .quantity(item.quantity())
                                .price(item.price())
                                .order(order)
                                .build()
                )
                .collect(Collectors.toList());

        order.setItems(items);

        return order;
    }
}
