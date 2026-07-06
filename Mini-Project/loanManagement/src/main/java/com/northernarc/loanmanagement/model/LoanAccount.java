package com.northernarc.loanmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanAccountId;

    @NotNull(message = "Loan start date cannot be null")
    @PastOrPresent(message = "Loan start date cannot be in the future")
    private LocalDate loanStartDate;

    @NotNull(message = "EMI due date cannot be null")
    private LocalDate emiDueDate;

    private LocalDate loanCloseDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Loan status cannot be null")
    private LoanStatus loanStatus;

    @NotNull(message = "Loan amount cannot be null")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotNull(message = "EMI amount cannot be null")
    @Positive(message = "EMI amount must be positive")
    private Double emiAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_product_id")
    @JsonIgnore
    private LoanProduct loanProduct;

    @JsonIgnore
    @OneToMany(mappedBy = "loanAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmiPayment> emiPayments = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (loanStatus == null) {
            loanStatus = LoanStatus.PENDING;
        }
    }
}