package br.com.andre.order.msorder.exception.handler;

import br.com.andre.order.msorder.exception.OrderNotFoundException;
import br.com.andre.order.msorder.exception.handler.dto.StandardHandlerErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<StandardHandlerErrorDto> orderNotFoundException(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(StandardHandlerErrorDto.builder()
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build()
                );
    }

}
