package br.com.andre.order.msorder.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void shouldCreateNewOrder() {
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

        order.setItems(List.of(item));

        assertEquals(1001L, order.getOrderId());
        assertEquals(1L, order.getCustomerId());
        assertEquals(1, order.getItems().size());
    }

}
