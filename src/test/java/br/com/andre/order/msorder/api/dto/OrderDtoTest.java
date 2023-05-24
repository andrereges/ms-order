package br.com.andre.order.msorder.api.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDtoTest {

    @Test
    void shouldCreateNewOrderDto() {
        var items = List.of(
                new ItemDto("Caneta", 10, 2.99)
        );

        var orderDto = new OrderDto(1001L, 1, 20.99, items);

        assertEquals(1001L, orderDto.orderId());
        assertEquals(1, orderDto.quantityItems());
        assertEquals(20.99, orderDto.totalOrder());
        assertEquals(1, orderDto.items().size());
    }

}
