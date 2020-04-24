package com.cml.eurder.domain.exceptions;

public class InputCanNotBeNullException extends RuntimeException{
    public InputCanNotBeNullException() {
        super("Input cannot be null");
    }
}