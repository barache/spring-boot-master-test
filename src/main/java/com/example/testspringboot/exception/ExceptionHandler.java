package com.example.testspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException
            (ResourceNotFoundException resourceNotFoundException)
    {
        Exception interviewException = new Exception(
                resourceNotFoundException.getMessage()
        );

        return new ResponseEntity<>(interviewException, HttpStatus.NOT_FOUND);
    }
}
