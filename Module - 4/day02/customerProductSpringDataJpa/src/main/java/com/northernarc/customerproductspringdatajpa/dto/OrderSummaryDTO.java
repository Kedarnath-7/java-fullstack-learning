package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class OrderSummaryDTO {

    private Long id;
    private LocalDate orderDate;
}
