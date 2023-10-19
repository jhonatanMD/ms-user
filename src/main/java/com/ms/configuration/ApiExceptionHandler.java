package com.ms.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<Object> handleBindException(org.springframework.validation.BindException ex) {


       var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(x -> !Objects.requireNonNull(x.getDefaultMessage()).isEmpty())
                .map(x ->  x.getField() + " " + x.getDefaultMessage())
                .collect(Collectors.joining(","));


        ErrorModel errorModel = new ErrorModel(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);

    }

    @ExceptionHandler(value = ResponseStatusException.class)
    protected ResponseEntity<Object> responseStatusException(ResponseStatusException ex ) {
        log.error("ApiExceptionHandler ::> ResponseStatusException ::> Error: " + ex.getMessage(), ex);
        ErrorModel errorModel = new ErrorModel(ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(errorModel);
    }

}
