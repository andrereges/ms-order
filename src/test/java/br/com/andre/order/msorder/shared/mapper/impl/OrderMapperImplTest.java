package br.com.andre.order.msorder.shared.mapper.impl;

import br.com.andre.order.msorder.domain.entity.Item;
import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.integration.broker.dto.ItemBrokerDto;
import br.com.andre.order.msorder.integration.broker.dto.OrderBrokerDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperImplTest {

    @Test
    void shouldMapperOrderToOrderDtoList() {
        var order = Order.builder()
                .orderId(1001L)
                .customerId(1L)
                .build();

        var item = Item.builder()
                .itemId(1L)
                .name("Caneta")
                .quantity(10)
                .price(12.99)
                .order(order)
                .build();

        var item2 = Item.builder()
                .itemId(2L)
                .name("Lápis")
                .quantity(15)
                .price(9.99)
                .order(order)
                .build();

        order.setItems(List.of(item, item2));

        var orders = List.of(order);

        var orderDtos = new OrderMapperImpl().ordersToOrderDtoList(orders);

        assertEquals(1001L, orderDtos.get(0).orderId());
        assertEquals(1, orderDtos.size());
        assertEquals(22.98, orderDtos.get(0).totalOrder());
        assertEquals(2, orderDtos.get(0).quantityItems());
        assertEquals(2, orderDtos.get(0).items().size());
    }

    @Test
    void shouldMapperOrderItemsToItemDto() {
        var item = Item.builder()
                .itemId(1L)
                .name("Caneta")
                .quantity(10)
                .price(12.99)
                .build();

        var item2 = Item.builder()
                .itemId(2L)
                .name("Estojo")
                .quantity(15)
                .price(9.99)
                .build();

        var order = Order.builder()
                .orderId(1001L)
                .customerId(1L)
                .items(List.of(item, item2))
                 .build();

        var orderDtos = new OrderMapperImpl().orderItemsToItemDto(order);

        assertNotNull(orderDtos);
        assertFalse(orderDtos.isEmpty());
        assertEquals(2, orderDtos.size());
        assertEquals("Caneta", orderDtos.get(0).name());
        assertEquals(10, orderDtos.get(0).quantity());
        assertEquals(12.99, orderDtos.get(0).price());
    }

    @Test
    void shouldMapperOrderBrokerDtoToOrderEntity() {
        var itemBrokerDto = new ItemBrokerDto("Estojo", 10, 9.99);
        var itemBrokerDto2 = new ItemBrokerDto("Caneta", 13, 2.99);

        var orderBrokerDto = new OrderBrokerDto(1001L, 1L, List.of(itemBrokerDto, itemBrokerDto2));

        var order = new OrderMapperImpl().orderBrokerDtoToOrderEntity(orderBrokerDto);

        assertNotNull(order);
        assertFalse(order.getItems().isEmpty());
        assertEquals(2, order.getItems().size());
        assertEquals(1001L, order.getOrderId());
        assertEquals(1L, order.getCustomerId());
        assertEquals("Estojo", order.getItems().get(0).getName());
        assertEquals(10, order.getItems().get(0).getQuantity());
        assertEquals(9.99, order.getItems().get(0).getPrice());
    }

}
