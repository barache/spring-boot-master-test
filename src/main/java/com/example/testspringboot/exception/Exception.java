package com.example.testspringboot.exception;

public class Exception {
    private final String message;


    public Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
