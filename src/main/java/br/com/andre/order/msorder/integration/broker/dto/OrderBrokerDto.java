package br.com.andre.order.msorder.integration.broker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderBrokerDto(
        @JsonProperty("codigoPedido")
        Long orderId,

        @JsonProperty("codigoCliente")
        Long customerId,

        @JsonProperty("itens")
        List<ItemBrokerDto> items
 ) {
}
