package br.com.andre.order.msorder.application.service.impl;

import br.com.andre.order.msorder.domain.entity.Order;
import br.com.andre.order.msorder.exception.OrderNotFoundException;
import br.com.andre.order.msorder.integration.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepositoryMock;

    @BeforeEach
    void setUp(){
        when(orderRepositoryMock.existsByOrderIdAndCustomerId(1001L, 1L))
                .thenReturn(Boolean.FALSE);

        when(orderRepositoryMock.existsByOrderIdAndCustomerId(1002L, 1L))
                .thenReturn(Boolean.TRUE);

        when(orderService.existsOrder(1010L, 1L))
                .thenReturn(Boolean.TRUE);

        when(orderRepositoryMock.save(any(Order.class)))
                .thenReturn(Order.builder()
                        .orderId(1040L)
                        .customerId(1L)
                        .build()
                );

        when(orderRepositoryMock.findByCustomerId(1L))
                .thenReturn(List.of(Order.builder()
                        .orderId(1080L)
                        .customerId(1L)
                        .build())
                );

        when(orderRepositoryMock.countOrderByCustomerId(1L))
                .thenReturn(3);

        when(orderRepositoryMock.getTotalValueOrderByOrderIdAndCustomerId(1010L, 1L))
                .thenReturn(2969.99);
    }

    @Test
    void shouldFalseWhenNotExistsOrderInRepository() {
        var existsOrder = orderService
                .existsOrder(1001L, 1L);

        assertThat(existsOrder)
                .isFalse();

        verify(orderRepositoryMock, times(1))
                .existsByOrderIdAndCustomerId(anyLong(), anyLong());
    }

    @Test
    void shouldTrueWhenExistsOrderInRepository() {
        var existsOrder = orderService
                .existsOrder(1002L, 1L);

        assertThat(existsOrder)
                .isTrue();

        verify(orderRepositoryMock, times(1))
                .existsByOrderIdAndCustomerId(anyLong(), anyLong());
    }

    @Test
    void shouldNotSaveWhenAlreadyExistsOrderToThisCustomer() {
        assertThatThrownBy(() ->
                orderService.save(
                        Order.builder()
                            .orderId(1010L)
                            .customerId(1L)
                            .build()
                )
        )
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage("Already exists order to this customer");


        verify(orderRepositoryMock, times(0))
                .save(any(Order.class));
    }

    @Test
    void shouldSaveWhenNotAlreadyExistsOrderToThisCustomer() {
        assertThat(orderService.save(
                Order.builder()
                        .orderId(1040L)
                        .customerId(1L)
                        .build()
        )).isNotNull();

        verify(orderRepositoryMock, times(1))
                .save(any(Order.class));
    }

    @Test
    void shouldReturnEmptyOrderListByCustomerIdWhenExistsNotDataInRepository() {
        assertThat(orderService.getOrdersByCustomerId(2L))
                .isEmpty();

        verify(orderRepositoryMock, times(1))
                .findByCustomerId(anyLong());
    }

    @Test
    void shouldReturnOrderListByCustomerIdWhenExistsDataInRepository() {
        assertThat(orderService.getOrdersByCustomerId(1L))
                .isNotEmpty();

        verify(orderRepositoryMock, times(1))
                .findByCustomerId(anyLong());
    }

    @Test
    void shouldReturnQuantityZeroOrdersByCustomerIdWhenNotExistsDataInRepository() {
        assertThat(orderService.getQuantityOrdersByCustomerId(2L))
                .isEqualTo(0);

        verify(orderRepositoryMock, times(1))
                .countOrderByCustomerId(anyLong());
    }

    @Test
    void shouldReturnQuantityOrdersByCustomerIdWhenExistsDataInRepository() {
        assertThat(orderService.getQuantityOrdersByCustomerId(1L))
                .isEqualTo(3);

        verify(orderRepositoryMock, times(1))
                .countOrderByCustomerId(anyLong());
    }

    @Test
    void shouldThrowsExceptionWhenNotExistsOrderInRepository() {
        assertThatThrownBy(() -> orderService.getTotalValueOrderByOrderIdAndCustomerId(9999L, 1L))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage("Order 9999 not found");

        verify(orderRepositoryMock, times(0))
                .getTotalValueOrderByOrderIdAndCustomerId(anyLong(), anyLong());
    }

    @Test
    void shouldReturnTotalValueOrdersByCustomerIdWhenExistsDataInRepository() {
        assertThat(orderService.getTotalValueOrderByOrderIdAndCustomerId(1010L, 1L))
                .isEqualTo(2969.99);

        verify(orderRepositoryMock, times(1))
                .getTotalValueOrderByOrderIdAndCustomerId(anyLong(), anyLong());
    }

}
