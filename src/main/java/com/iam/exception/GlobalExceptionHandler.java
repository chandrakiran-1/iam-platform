package com.iam.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e)
    {
                 return ResponseEntity
                         .status(500)
                         .body(e.getMessage());

    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e)
    {
        return ResponseEntity
                .status(404)
                .body(e.getMessage());
    }
}
