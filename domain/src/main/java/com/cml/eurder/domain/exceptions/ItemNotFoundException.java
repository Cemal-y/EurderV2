package com.cml.eurder.domain.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String keyword) {
        System.out.println("The item with given " + keyword + " is not fount" );
    }
}
