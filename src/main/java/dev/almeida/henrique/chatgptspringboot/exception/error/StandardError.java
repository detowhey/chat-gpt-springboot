package dev.almeida.henrique.chatgptspringboot.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record StandardError(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
        Instant instant,
        Integer statusCode,
        String error,
        String message,
        String path
) {
}
