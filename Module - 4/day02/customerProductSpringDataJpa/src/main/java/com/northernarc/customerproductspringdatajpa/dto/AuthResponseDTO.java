package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private Long customerId;
    private String email;
    private String role;
}
