package com.cml.eurder.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String keyword) {
        super("The user with given " + keyword + " is not found" );
    }
}
