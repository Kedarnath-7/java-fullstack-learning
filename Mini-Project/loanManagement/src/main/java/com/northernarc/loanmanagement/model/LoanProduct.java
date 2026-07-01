package com.northernarc.loanmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanCode;

    @NotBlank(message = "Loan name cannot be empty")
    private String loanName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Loan type cannot be null")
    private LoanType loanType;

    @NotNull(message = "Interest rate cannot be null")
    @Positive(message = "Interest rate must be positive")
    private Double interestRate;

    @NotNull(message = "Daily penalty rate cannot be null")
    @PositiveOrZero(message = "Daily penalty rate must be positive or zero")
    private Double dailyPenaltyRate;

    @OneToMany(mappedBy = "loanProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanAccount> loanAccounts = new ArrayList<>();

}
