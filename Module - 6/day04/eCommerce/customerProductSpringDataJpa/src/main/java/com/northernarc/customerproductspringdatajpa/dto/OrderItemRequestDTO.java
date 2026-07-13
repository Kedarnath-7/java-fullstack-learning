package com.northernarc.customerproductspringdatajpa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemRequestDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be at least 1")
    @Min(1)
    private int quantity;

}
