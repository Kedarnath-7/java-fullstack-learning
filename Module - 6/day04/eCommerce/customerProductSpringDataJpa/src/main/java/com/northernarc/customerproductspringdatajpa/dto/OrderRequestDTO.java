package com.northernarc.customerproductspringdatajpa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDate orderDate;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemRequestDTO> orderItems;
}
