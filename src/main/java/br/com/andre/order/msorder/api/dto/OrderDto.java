package br.com.andre.order.msorder.api.dto;

import java.util.List;

public record OrderDto(
        Long orderId,
        Integer quantityItems,
        Double totalOrder,
        List<ItemDto> items
 ) {
}
