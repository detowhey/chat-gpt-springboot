package dev.almeida.henrique.chatgptspringboot.exception.handler;

import dev.almeida.henrique.chatgptspringboot.exception.error.StandardErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<StandardErrorResponse> createResponseError(HttpStatus httpStatus, String error, Exception exception, ServletServerHttpRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse(
                Instant.now(),
                httpStatus.value(),
                error,
                exception.getMessage(),
                request.getURI().toString()
        );
        log.error(error, exception.getCause());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

