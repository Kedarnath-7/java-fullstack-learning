package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemSummaryDTO {

    private Long id;
    private int quantity;


}
