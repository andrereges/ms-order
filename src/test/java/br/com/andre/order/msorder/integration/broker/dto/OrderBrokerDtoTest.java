package br.com.andre.order.msorder.integration.broker.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderBrokerDtoTest {

    @Test
    void shouldCreateNewOrderBrokerDto() {
        var items = List.of(
                new ItemBrokerDto("Caneta", 10, 2.99)
        );

        var orderBrokerDto = new OrderBrokerDto(1001L, 1L,  items);

        assertEquals(1001L, orderBrokerDto.orderId());
        assertEquals(1L, orderBrokerDto.customerId());
        assertEquals(1, orderBrokerDto.items().size());
    }

}
