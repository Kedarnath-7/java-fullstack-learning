package com.northernarc.customerproductspringdatajpa.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }
}
