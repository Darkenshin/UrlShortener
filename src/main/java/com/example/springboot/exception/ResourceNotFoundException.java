package com.example.springboot.exception;

public class ResourceNotFoundException extends Exception {


    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}