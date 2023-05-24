package br.com.andre.order.msorder.application.service.impl;

import br.com.andre.order.msorder.application.service.OrderService;
import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.exception.OrderNotFoundException;
import br.com.andre.order.msorder.integration.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        if (this.existsOrder(order.getOrderId(), order.getCustomerId())) {
            throw new OrderNotFoundException("Already exists order to this customer");
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Integer getQuantityOrdersByCustomerId(Long customerId) {
        return orderRepository.countOrderByCustomerId(customerId);
    }

    @Override
    public Double getTotalValueOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {
        if (!this.existsOrder(orderId, customerId))
            throw new OrderNotFoundException(orderId);

        return orderRepository.getTotalValueOrderByOrderIdAndCustomerId(orderId, customerId);
    }

    public boolean existsOrder(Long orderId, Long customerId) {
        return orderRepository.existsByOrderIdAndCustomerId(orderId, customerId);
    }

}
