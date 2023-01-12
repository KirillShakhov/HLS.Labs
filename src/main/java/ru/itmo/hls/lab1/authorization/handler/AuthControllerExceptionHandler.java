package ru.itmo.hls.lab1.authorization.handler;

import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.hls.exception.RefreshTokenException;
import ru.itmo.hls.exception.UniqueEmailException;
import ru.itmo.hls.exception.UniquePhoneNumberException;
import ru.itmo.hls.exception.UniqueUsernameException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<Object> handleRefreshToken(@NonNull RuntimeException exception,
                                                     @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFound(@NonNull RuntimeException exception,
                                                         @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<Object> handleIllegalArgument(@NonNull RuntimeException exception,
                                                        @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UniqueEmailException.class)
    public ResponseEntity<Object> handleUniqueEmail(@NonNull RuntimeException exception,
                                                    @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UniquePhoneNumberException.class)
    public ResponseEntity<Object> handleUniquePhoneNumber(@NonNull RuntimeException exception,
                                                          @NonNull WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
