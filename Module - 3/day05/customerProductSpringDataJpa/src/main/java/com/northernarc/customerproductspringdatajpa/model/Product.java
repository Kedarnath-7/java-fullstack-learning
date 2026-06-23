package com.northernarc.customerproductspringdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

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

    @OneToMany
    private OrderItem orderItem;


}
