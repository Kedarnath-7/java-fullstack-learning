package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Customer summary projection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDTO {
    private String customerName;
    private String branch;
    private Integer numberOfLoans;
    private Double totalLoanAmount;
    private Double totalPenaltyPaid;
}
