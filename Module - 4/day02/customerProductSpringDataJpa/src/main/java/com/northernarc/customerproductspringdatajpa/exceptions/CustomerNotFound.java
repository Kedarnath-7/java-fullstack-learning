package com.northernarc.customerproductspringdatajpa.exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(String message) {
        super(message);
    }
}
