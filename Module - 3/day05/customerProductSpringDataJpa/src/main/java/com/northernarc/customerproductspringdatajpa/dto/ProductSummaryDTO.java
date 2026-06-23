package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSummaryDTO {
    private Long product_id;

    private String name;

    private String brand;

    private double cost;

}
