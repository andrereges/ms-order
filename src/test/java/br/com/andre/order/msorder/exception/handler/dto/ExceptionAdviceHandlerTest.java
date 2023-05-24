package br.com.andre.order.msorder.exception.handler.dto;

import br.com.andre.order.msorder.exception.OrderNotFoundException;
import br.com.andre.order.msorder.exception.handler.ExceptionAdviceHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionAdviceHandlerTest {

    @Test
    void testReturnStandardHandlerErrorDtoWhenThrowsException() {
        ResponseEntity<StandardHandlerErrorDto> responseEntity = new ExceptionAdviceHandler()
                .orderNotFoundException(new OrderNotFoundException("Exception message"));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Exception message", responseEntity.getBody().getMessage());
    }

}
