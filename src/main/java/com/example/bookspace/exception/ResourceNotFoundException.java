package com.example.bookspace.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resource) {
        super(resource + " not found.");
    }
}