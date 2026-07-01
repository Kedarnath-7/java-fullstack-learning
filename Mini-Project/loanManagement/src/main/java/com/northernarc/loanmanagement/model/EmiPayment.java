package com.northernarc.loanmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @PositiveOrZero(message = "Penalty paid must be positive")
    private Double penaltyPaid;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type cannot be null")
    private PaymentType paymentType;

    @PastOrPresent(message = "Payment date cannot be in the future")
    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_account_id")
    private LoanAccount loanAccount;
}
