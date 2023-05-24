package br.com.andre.order.msorder.api.controller;

import br.com.andre.order.msorder.api.dto.OrderDto;
import br.com.andre.order.msorder.exception.handler.dto.StandardHandlerErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Order", description = "Responsible for returning order information")
public interface OrderController {

    /**
     * Endpoint to get orders by customer id
     * @param customerId type Long
     * @return ResponseEntity<List<OrderDto>>
     */
    @Operation(
            summary = "Find all orders by customer",
            description = "Find all orders with all items",
            tags = { "Order" }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Request was processed and answered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class)))
    })
    ResponseEntity<List<OrderDto>> getOrdersByCustomerId(Long customerId);

    /**
     * Endpoint to get quantity of orders by customer id
     * @param customerId type Long
     * @return ResponseEntity<List<OrderDto>>
     */
    @Operation(
            summary = "Get quantity of orders by customer",
            description = "Get quantity of orders by customer",
            tags = { "Order" }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Request was processed and answered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class)))
    })
    ResponseEntity<Integer> getQuantityOrdersByCustomerId(Long customerId);

    /**
     * Endpoint to get total value of orders by order id and customer id
     * @param customerId type Long
     * @return ResponseEntity<List<OrderDto>>
     */
    @Operation(
            summary = "Get value total of order by order id customer id",
            description = "Get value total of order by order id customer id",
            tags = { "Order" }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Request was processed and answered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found to customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardHandlerErrorDto.class)))
    })
    ResponseEntity<Double> getTotalValueOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

}
