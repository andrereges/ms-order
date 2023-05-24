package br.com.andre.order.msorder.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        super(String.format("Order %d not found", orderId));
    }

}
