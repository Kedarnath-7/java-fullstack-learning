package com.northernarc.customerproductspringdatajpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "First name is required")
    private String fName;
    @NotBlank(message = "Last name is required")
    private String lName;
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

}
