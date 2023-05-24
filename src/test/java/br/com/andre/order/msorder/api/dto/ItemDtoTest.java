package br.com.andre.order.msorder.api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoTest {

    @Test
    void shouldCreateNewItemDto() {
        var item = new ItemDto("Caneta", 10, 2.99);

        assertEquals("Caneta", item.name());
        assertEquals(10, item.quantity());
        assertEquals(2.99, item.price());
    }

}
