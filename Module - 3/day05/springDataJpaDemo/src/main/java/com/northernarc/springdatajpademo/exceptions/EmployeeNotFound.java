package com.northernarc.springdatajpademo.exceptions;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound(String message) {
        super(message);
    }
}
