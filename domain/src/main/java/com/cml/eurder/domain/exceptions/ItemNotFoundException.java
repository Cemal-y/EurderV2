package com.cml.eurder.domain.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String keyword) {
        super("The item with given " + keyword + " is not found" );
    }
}
