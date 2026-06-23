package com.northernarc.customerproductspringdatajpa.controllerAdvice;

import com.northernarc.customerproductspringdatajpa.exceptions.CustomerNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.OrderItemNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.OrderNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<ErrorResponse> orderNotFound(OrderNotFound e){
        ErrorResponse er = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponse> productNotFound(ProductNotFound e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ErrorResponse> customerNotFound(CustomerNotFound e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderItemNotFound.class)
    public ResponseEntity<ErrorResponse> orderItemNotFound(OrderItemNotFound e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
