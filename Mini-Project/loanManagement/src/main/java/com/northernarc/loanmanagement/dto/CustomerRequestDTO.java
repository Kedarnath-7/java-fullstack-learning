package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Customer create/update request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Customer name cannot be empty")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 120, message = "Password must be between 8 and 120 characters")
    private String password;

    @NotBlank(message = "Branch cannot be empty")
    @Size(min = 2, max = 100, message = "Branch must be between 2 and 100 characters")
    private String branch;

    @NotBlank(message = "Role cannot be empty")
    private String role;
}