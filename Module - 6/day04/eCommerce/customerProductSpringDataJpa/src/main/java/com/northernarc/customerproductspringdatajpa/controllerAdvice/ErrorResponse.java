package com.northernarc.customerproductspringdatajpa.controllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;

}
