package com.northernarc.loanmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Loan code cannot be empty")
    @Size(max = 20, message = "Loan code must not exceed 20 characters")
    @Column(nullable = false, length = 20)
    private String loanCode;

    @NotBlank(message = "Loan name cannot be empty")
    @Size(min = 2, max = 120, message = "Loan name must be between 2 and 120 characters")
    @Column(nullable = false)
    private String loanName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Loan type cannot be null")
    @Column(nullable = false)
    private LoanType loanType;

    @NotNull(message = "Interest rate cannot be null")
    @Positive(message = "Interest rate must be positive")
    @Column(nullable = false)
    private Double interestRate;

    @NotNull(message = "Daily penalty rate cannot be null")
    @PositiveOrZero(message = "Daily penalty rate must be positive or zero")
    @Column(nullable = false)
    private Double dailyPenaltyRate;

    @JsonIgnore
    @OneToMany(mappedBy = "loanProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanAccount> loanAccounts = new ArrayList<>();
}
