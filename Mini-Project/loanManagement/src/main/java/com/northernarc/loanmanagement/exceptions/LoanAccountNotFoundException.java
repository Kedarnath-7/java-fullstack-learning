package com.northernarc.loanmanagement.exceptions;

public class LoanAccountNotFoundException extends RuntimeException {
    public LoanAccountNotFoundException(String message) {
        super(message);
    }
}
