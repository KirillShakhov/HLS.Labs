package ru.itmo.hls.facade.handler;

import feign.FeignException;
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FacadeControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Object> handleAccessToken(@NonNull RuntimeException exception,
                                                     @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<Object> handleBadRequest(@NonNull RuntimeException exception,
                                                     @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
