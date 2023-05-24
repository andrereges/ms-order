package br.com.andre.order.msorder.api.controller.impl;

import br.com.andre.order.msorder.api.dto.ItemDto;
import br.com.andre.order.msorder.api.dto.OrderDto;
import br.com.andre.order.msorder.application.service.OrderService;
import br.com.andre.order.msorder.exception.OrderNotFoundException;
import br.com.andre.order.msorder.shared.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp(){
        when(orderService.getQuantityOrdersByCustomerId(1L))
                .thenReturn(3);

        when(orderService.getTotalValueOrderByOrderIdAndCustomerId(1005L, 1L))
                .thenThrow(new OrderNotFoundException(1005L));

        when(orderService.getTotalValueOrderByOrderIdAndCustomerId(1001L, 1L))
                .thenReturn(79.98);

        when(orderMapper.ordersToOrderDtoList(anyList()))
                .thenReturn(createOrderDtoList());
    }

    @Test
    void shouldReturnCustomerListOrdersWhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/v1/orders/customer/1/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId").value(1001))
                .andExpect(jsonPath("$.[0].quantityItems").value(1))
                .andExpect(jsonPath("$.[0].totalOrder").value(9.99))
                .andExpect(jsonPath("$.[0].items").exists())
                .andExpect(jsonPath("$.[0].items").isNotEmpty())
                .andExpect(jsonPath("$.[0].items.[0].name").value("Caneta"))
                .andExpect(jsonPath("$.[0].items.[0].quantity").value(10))
                .andExpect(jsonPath("$.[0].items.[0].price").value(9.99))
        ;
    }

    @Test
    void shouldReturnQuantityOrderByCustomerWhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/v1/orders/customer/1/quantity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(3))
        ;
    }

    @Test
    void shouldThrowsStatus404WhenNotFoundOrderInGetTotal() throws Exception {
        this.mockMvc.perform(get("/v1/orders/1005/customer/1/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus").value(404))
                .andExpect(jsonPath("$.message").value("Order 1005 not found"))
                .andExpect(jsonPath("$.timestamp").exists())
        ;
    }

    @Test
    void shouldReturnTotalValueOrderByCustomerWhenSuccessful() throws Exception {
        this.mockMvc.perform(get("/v1/orders/1001/customer/1/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(79.98))
        ;
    }

    private static List<OrderDto> createOrderDtoList() {
        var items = List.of(new ItemDto("Caneta", 10, 9.99));

        var orderDto = new OrderDto(1001L, 1, 9.99, items);
        return List.of(
                orderDto
        );
    }

}
