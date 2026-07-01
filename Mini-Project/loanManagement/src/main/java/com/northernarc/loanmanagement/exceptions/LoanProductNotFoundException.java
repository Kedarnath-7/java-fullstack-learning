package com.northernarc.loanmanagement.exceptions;

public class LoanProductNotFoundException extends RuntimeException {
    public LoanProductNotFoundException(String message) {
        super(message);
    }
}
