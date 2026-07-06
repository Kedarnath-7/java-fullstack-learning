package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Login response payload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
