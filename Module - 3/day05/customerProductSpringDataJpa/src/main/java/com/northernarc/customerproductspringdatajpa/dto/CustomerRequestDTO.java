package com.northernarc.customerproductspringdatajpa.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerRequestDTO {

    @NotBlank
    private String fName;
    @NotBlank
    private String lName;
    @Email
    private String email;

}

