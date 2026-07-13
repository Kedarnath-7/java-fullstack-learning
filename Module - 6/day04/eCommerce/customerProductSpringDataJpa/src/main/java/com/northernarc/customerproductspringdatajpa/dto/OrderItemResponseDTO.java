package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemResponseDTO {

    private Long id;
    private OrderSummaryDTO orderSummaryDTO;
    private ProductSummaryDTO productSummaryDTO;
    private int quantity;
}
