package com.emakers.demo.exceptions.general;

import com.emakers.demo.exceptions.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundHandler(EntityNotFoundException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<RestErrorMessage>> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<RestErrorMessage> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new RestErrorMessage(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<List<RestErrorMessage>> httpMessageNotReadableHandler(HttpMessageNotReadableException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, "Invalid JSON format. Please check the data and try again.");
        List<RestErrorMessage> errors = Collections.singletonList(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<RestErrorMessage> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not supported. Please use one of the supported methods: " + exception.getSupportedHttpMethods());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<RestErrorMessage> handleMissingServletRequestParameter(MissingServletRequestParameterException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, "Required parameter is missing: " + exception.getParameterName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    private ResponseEntity<RestErrorMessage> handleBookNotAvailable(BookNotAvailableException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EntityReferencedException.class)
    private ResponseEntity<RestErrorMessage> entityReferencedHandler(EntityReferencedException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
