package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "EMI payment response model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPaymentResponseDTO {
    private Long paymentId;
    private Double amountPaid;
    private Double penaltyPaid;
    private String paymentType;
    private LocalDate paymentDate;
    private Long loanAccountId;
}
