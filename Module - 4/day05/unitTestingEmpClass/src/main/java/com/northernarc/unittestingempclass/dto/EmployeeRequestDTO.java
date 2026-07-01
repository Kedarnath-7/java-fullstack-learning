package com.northernarc.unittestingempclass.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Salary cannot be null")
    private Double salary;
}
