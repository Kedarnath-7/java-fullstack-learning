package com.northernarc.codingassessment5.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    private Long accountId;
    private BigDecimal amount;
}
