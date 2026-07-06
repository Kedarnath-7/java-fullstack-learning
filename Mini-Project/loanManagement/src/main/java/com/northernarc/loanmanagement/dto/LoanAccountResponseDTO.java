package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Loan account response model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAccountResponseDTO {
    private Long loanAccountId;
    private LocalDate loanStartDate;
    private LocalDate emiDueDate;
    private LocalDate loanCloseDate;
    private String loanStatus;
    private Double loanAmount;
    private Double emiAmount;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String loanCode;
    private String loanType;
}
