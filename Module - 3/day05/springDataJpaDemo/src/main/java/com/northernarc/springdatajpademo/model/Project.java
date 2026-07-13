package com.northernarc.springdatajpademo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @Valid
    private List<Employee> employeeList = new ArrayList<>();
}
