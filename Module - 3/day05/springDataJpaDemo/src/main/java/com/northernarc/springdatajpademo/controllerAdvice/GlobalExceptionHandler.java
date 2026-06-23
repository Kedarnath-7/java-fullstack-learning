package com.northernarc.springdatajpademo.controllerAdvice;

import com.northernarc.springdatajpademo.exceptions.EmployeeNotFound;
import com.northernarc.springdatajpademo.exceptions.ProjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFound.class)
    public ResponseEntity<ErrorResponse> projectHandler(ProjectNotFound e){
        ErrorResponse er = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorResponse> employeeHandler(EmployeeNotFound e){
        ErrorResponse er = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

}
