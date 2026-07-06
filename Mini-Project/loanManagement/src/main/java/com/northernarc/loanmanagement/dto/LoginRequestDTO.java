package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Login request payload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Schema(example = "rahul.sharma@northernarc.com")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 120, message = "Password must be between 8 and 120 characters")
    @Schema(example = "password123")
    private String password;
}
