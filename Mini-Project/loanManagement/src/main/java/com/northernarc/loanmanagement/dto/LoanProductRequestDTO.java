package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Loan product create/update request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductRequestDTO {

    @NotBlank(message = "Loan code cannot be empty")
    @Size(max = 20, message = "Loan code must not exceed 20 characters")
    private String loanCode;

    @NotBlank(message = "Loan name cannot be empty")
    @Size(min = 2, max = 120, message = "Loan name must be between 2 and 120 characters")
    private String loanName;

    @NotBlank(message = "Loan type cannot be empty")
    private String loanType;

    @NotNull(message = "Interest rate cannot be null")
    @Positive(message = "Interest rate must be positive")
    private Double interestRate;

    @NotNull(message = "Daily penalty rate cannot be null")
    @PositiveOrZero(message = "Daily penalty rate must be positive or zero")
    private Double dailyPenaltyRate;
}