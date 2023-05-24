package br.com.andre.order.msorder.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNotFoundExceptionTest {

    @Test
    void shouldCreateNewOrderNotFoundExceptionWhenHasOnlyOrderIdArgumentInConstruct() {
        var exception = new OrderNotFoundException(1001L);

        assertEquals("Order 1001 not found", exception.getMessage());
    }

    @Test
    void shouldCreateNewOrderNotFoundExceptionWhenHasMessageArgumentInConstruct() {
        var exception = new OrderNotFoundException("Order 1001 not found");

        assertEquals("Order 1001 not found", exception.getMessage());
    }

}
