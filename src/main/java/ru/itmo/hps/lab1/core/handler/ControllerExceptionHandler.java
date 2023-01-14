package ru.itmo.hps.lab1.core.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.hls.exception.AlreadyExistsException;
import ru.itmo.hls.exception.NotFoundException;
import ru.itmo.hls.exception.PageNotFoundException;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlePageNotFound(@NonNull RuntimeException exception, @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(@NonNull RuntimeException exception, @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(@NonNull RuntimeException exception, @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<Object> handlePageNotFoundException(@NonNull RuntimeException exception, @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
