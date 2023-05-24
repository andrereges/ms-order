package br.com.andre.order.msorder.exception.handler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardHandlerErrorDto {

    private int httpStatus;

    private Object message;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);

}
