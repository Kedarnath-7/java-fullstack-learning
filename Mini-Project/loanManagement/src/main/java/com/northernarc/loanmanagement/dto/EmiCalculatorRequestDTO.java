package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "EMI calculator request payload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiCalculatorRequestDTO {

    @NotNull(message = "Loan amount cannot be null")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotNull(message = "Annual interest rate cannot be null")
    @PositiveOrZero(message = "Annual interest rate must be zero or positive")
    private Double annualInterestRate;

    @NotNull(message = "Tenure in months cannot be null")
    @Positive(message = "Tenure in months must be positive")
    private Integer tenureInMonths;
}
