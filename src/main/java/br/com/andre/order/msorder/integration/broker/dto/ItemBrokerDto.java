package br.com.andre.order.msorder.integration.broker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemBrokerDto(
        @JsonProperty("produto")
        String name,

        @JsonProperty("quantidade")
        Integer quantity,

        @JsonProperty("preco")
        Double price
 ) { }
