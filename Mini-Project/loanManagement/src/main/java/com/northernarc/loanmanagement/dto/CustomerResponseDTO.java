package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Customer response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private Long customerId;
    private String customerName;
    private String email;
    private String branch;
    private String role;
}