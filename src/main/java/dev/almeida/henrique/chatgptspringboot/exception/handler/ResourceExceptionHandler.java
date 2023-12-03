package dev.almeida.henrique.chatgptspringboot.exception.handler;

import dev.almeida.henrique.chatgptspringboot.exception.*;
import dev.almeida.henrique.chatgptspringboot.exception.error.StandardErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AssistantNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> assistantNotFound(
            AssistantNotFoundException exception,
            ServletServerHttpRequest request
    ) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", exception, request);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> fileNotFound(
            FileNotFoundException exception,
            ServletServerHttpRequest request
    ) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", exception, request);
    }

    @ExceptionHandler(MessaUserNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> messageNotFound(
            MessaUserNotFoundException exception,
            ServletServerHttpRequest request
    ) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", exception, request);
    }

    @ExceptionHandler(RunNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> runNotFound(
            RunNotFoundException exception,
            ServletServerHttpRequest request
    ) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", exception, request);
    }

    @ExceptionHandler(ThreadBotNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> threadBotNotFound(
            ThreadBotNotFoundException exception,
            ServletServerHttpRequest request
    ) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", exception, request);
    }

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
