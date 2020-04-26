package com.cml.eurder.domain.exceptions;

public class OrderDoesNotBelongToCustomerException extends RuntimeException {
    public OrderDoesNotBelongToCustomerException(long orderId) {
        super("The order with id: " + orderId + " is not belong to you. It cannot be reordered");
    }
}
