package com.example.socialmedia.exception;

public class RequiredParameterMissingException extends RuntimeException {
    public RequiredParameterMissingException(String message) {
        super(message);
    }
}
