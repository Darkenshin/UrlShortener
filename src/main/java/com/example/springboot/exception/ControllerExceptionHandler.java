package com.example.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>( errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlgorithmException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(AlgorithmException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>( errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), request.getContextPath());

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}