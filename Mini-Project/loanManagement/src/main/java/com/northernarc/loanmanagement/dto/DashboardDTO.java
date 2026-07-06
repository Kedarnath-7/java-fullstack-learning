package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Dashboard analytics response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private Long totalCustomers;
    private Long totalLoans;
    private Double totalLoanAmountDisbursed;
    private Double totalPenaltyCollected;
    private String topBranch;
    private String highestLoanCustomer;
}
