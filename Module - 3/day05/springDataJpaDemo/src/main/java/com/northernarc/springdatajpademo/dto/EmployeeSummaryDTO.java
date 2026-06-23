package com.northernarc.springdatajpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeSummaryDTO {
    private Long id;
    private String name;
}
