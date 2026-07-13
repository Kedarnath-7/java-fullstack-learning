package com.northernarc.customerproductspringdatajpa.exceptions;

public class OrderItemNotFound extends RuntimeException {
    public OrderItemNotFound(String message) {
        super(message);
    }
}
