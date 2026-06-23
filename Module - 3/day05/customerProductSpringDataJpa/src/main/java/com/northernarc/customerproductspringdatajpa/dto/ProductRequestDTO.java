package com.northernarc.customerproductspringdatajpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotBlank
    private String category;

    @Positive
    private double cost;

    @Positive
    private int stock;
}
