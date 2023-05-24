package br.com.andre.order.msorder.application.service;

import br.com.andre.order.msorder.domain.entity.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);
    List<Order> getOrdersByCustomerId(Long customerId);

    Integer getQuantityOrdersByCustomerId(Long customerId);

    Double getTotalValueOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

}
