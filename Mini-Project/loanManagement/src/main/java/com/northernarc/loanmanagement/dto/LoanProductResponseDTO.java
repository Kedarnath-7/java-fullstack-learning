package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Loan product response model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductResponseDTO {
    @Schema(example = "LP001")
    private String loanCode;
    @Schema(example = "Home Priority Loan")
    private String loanName;
    @Schema(example = "HOME")
    private String loanType;
    @Schema(example = "8.5")
    private Double interestRate;
    @Schema(example = "2.8")
    private Double dailyPenaltyRate;
}
