package com.northernarc.loanmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emiPaymentId;

    @NotNull(message = "Amount paid cannot be null")
    @Positive(message = "Amount paid must be positive")
    private Double amountPaid;

    @NotNull(message = "Penalty paid cannot be null")
    @PositiveOrZero(message = "Penalty paid must be positive or zero")
    private Double penaltyPaid;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type cannot be null")
    private PaymentType paymentType;

    @PastOrPresent(message = "Payment date cannot be in the future")
    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_account_id")
    @JsonIgnore
    private LoanAccount loanAccount;
}
