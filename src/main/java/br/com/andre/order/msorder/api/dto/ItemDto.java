package br.com.andre.order.msorder.api.dto;

public record ItemDto(
        String name,
        Integer quantity,
        Double price
 ) { }
