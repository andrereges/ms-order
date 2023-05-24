package br.com.andre.order.msorder.exception.handler.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardHandlerErrorDtoTest {

    @Test
    void shouldCreateNewStandardHandlerErrorDto() {
        var errorDto = StandardHandlerErrorDto.builder()
                .httpStatus(400)
                .message("Message")
                .timestamp(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0))
                .build();

        assertEquals(400, errorDto.getHttpStatus());
        assertEquals("Message", errorDto.getMessage());
        assertEquals("2023-01-01T00:00", errorDto.getTimestamp().toString());
    }

}
