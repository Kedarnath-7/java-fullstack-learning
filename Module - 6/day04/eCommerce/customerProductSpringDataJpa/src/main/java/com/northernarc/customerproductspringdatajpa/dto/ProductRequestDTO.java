package com.northernarc.customerproductspringdatajpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Cost must be a positive value")
    private double cost;

    @Positive(message = "Stock must be a non-negative value")
    private int stock;
}
