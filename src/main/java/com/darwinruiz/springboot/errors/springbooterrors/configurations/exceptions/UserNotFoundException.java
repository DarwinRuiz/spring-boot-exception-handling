package com.darwinruiz.springboot.errors.springbooterrors.configurations.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
