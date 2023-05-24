package br.com.andre.order.msorder.integration.broker.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemBrokerDtoTest {

    @Test
    void shouldCreateNewItemBrokerDto() {
        var itemBrokerDto = new ItemBrokerDto("Caneta", 10, 2.99);

        assertEquals("Caneta", itemBrokerDto.name());
        assertEquals(10, itemBrokerDto.quantity());
        assertEquals(2.99, itemBrokerDto.price());
    }

}
