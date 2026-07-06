package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "EMI payment request payload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPaymentDTO {
    @NotNull(message = "Amount paid cannot be null")
    @Positive(message = "Amount paid must be positive")
    private Double amountPaid;

    @NotNull(message = "Penalty paid cannot be null")
    @PositiveOrZero(message = "Penalty paid must be zero or positive")
    private Double penaltyPaid;

    @NotBlank(message = "Payment type cannot be empty")
    private String paymentType;

    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;

    @NotNull(message = "Loan account ID cannot be null")
    private Long loanAccountId;
}
