package com.northernarc.unittestingempclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private Double salary;
}
