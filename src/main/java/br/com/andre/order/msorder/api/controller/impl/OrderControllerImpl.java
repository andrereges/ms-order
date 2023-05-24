package br.com.andre.order.msorder.api.controller.impl;

import br.com.andre.order.msorder.api.controller.OrderController;
import br.com.andre.order.msorder.api.dto.OrderDto;
import br.com.andre.order.msorder.application.service.OrderService;
import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.shared.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    @GetMapping("/customer/{customerId}/all")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        return ResponseEntity.ok(orderMapper.ordersToOrderDtoList(orders));
    }

    @Override
    @GetMapping("/customer/{customerId}/quantity")
    public ResponseEntity<Integer> getQuantityOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(orderService.getQuantityOrdersByCustomerId(customerId));
    }

    @Override
    @GetMapping("/{orderId}/customer/{customerId}/total")
    public ResponseEntity<Double> getTotalValueOrderByOrderIdAndCustomerId(
            @PathVariable("orderId") Long orderId,
            @PathVariable("customerId") Long customerId
    ) {
        return ResponseEntity.ok(orderService.getTotalValueOrderByOrderIdAndCustomerId(orderId, customerId));
    }

}
