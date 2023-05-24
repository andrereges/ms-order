package br.com.andre.order.msorder.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemTest {

    @Test
    void shouldCreateNewItem() {
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

        assertEquals(1L, item.getItemId());
        assertEquals("Caneta", item.getName());
        assertEquals(10, item.getQuantity());
        assertEquals(12.99, item.getPrice());
        assertNotNull(item.getOrder());
    }

}
