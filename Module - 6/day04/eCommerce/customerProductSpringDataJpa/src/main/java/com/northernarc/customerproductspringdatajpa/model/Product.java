package com.northernarc.customerproductspringdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products_ecom")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Brand is required")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @Positive(message = "Cost must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(nullable = false)
    private int stock;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

}
