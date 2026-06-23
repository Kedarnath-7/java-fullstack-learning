package com.northernarc.customerproductspringdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private Long product_id;

    private String name;
    private String brand;
    private String category;
    private double cost;
    private int stock;
}
