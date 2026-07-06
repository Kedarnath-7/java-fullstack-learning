package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Loan account create/update request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAccountRequestDTO {

    @NotNull(message = "Loan start date cannot be null")
    private LocalDate loanStartDate;

    @NotNull(message = "EMI due date cannot be null")
    private LocalDate emiDueDate;

    @NotNull(message = "Loan amount cannot be null")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotNull(message = "EMI amount cannot be null")
    @Positive(message = "EMI amount must be positive")
    private Double emiAmount;

    @NotBlank(message = "Loan code cannot be empty")
    private String loanCode;

    private Long customerId;
}