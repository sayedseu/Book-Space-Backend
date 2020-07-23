package com.example.bookspace.exception;

public class ResourceAlreadyExistException extends Exception {
    public ResourceAlreadyExistException(String source) {
        super(source + " already exist.");
    }
}