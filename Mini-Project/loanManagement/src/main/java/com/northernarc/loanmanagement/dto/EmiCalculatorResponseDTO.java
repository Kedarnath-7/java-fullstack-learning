package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "EMI calculator response model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiCalculatorResponseDTO {
    private Double loanAmount;
    private Double annualInterestRate;
    private Integer tenureInMonths;
    private Double monthlyEmi;
    private Double totalPayableAmount;
    private Double totalInterestPayable;
}
