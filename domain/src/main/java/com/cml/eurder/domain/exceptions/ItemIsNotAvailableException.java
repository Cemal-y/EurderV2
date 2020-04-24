package com.cml.eurder.domain.exceptions;

public class ItemIsNotAvailableException extends RuntimeException{
    public ItemIsNotAvailableException() {
        super("Item is not available");
    }
}
