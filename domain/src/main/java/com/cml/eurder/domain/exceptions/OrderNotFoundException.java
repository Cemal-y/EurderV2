package com.cml.eurder.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String keyword) {
        super("The order with given " + keyword + " is not fount" );
    }
}
