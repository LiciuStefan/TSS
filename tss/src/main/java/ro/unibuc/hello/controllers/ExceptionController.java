package ro.unibuc.hello.controllers;

import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ro.unibuc.hello.exceptions.EntityAlreadyExistsException;
import ro.unibuc.hello.exceptions.EntityNotFoundException;
import ro.unibuc.hello.exceptions.UserNotAuthorizedException;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<?> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception,
                                            WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>(Map.of("message", exception.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception,
                                            WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>(Map.of("message", exception.getMessage()),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<?> handleUserNotAuthorizedException(UserNotAuthorizedException exception,
                                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>(Map.of("message", exception.getMessage()),
                HttpStatus.FORBIDDEN);
    }
}
