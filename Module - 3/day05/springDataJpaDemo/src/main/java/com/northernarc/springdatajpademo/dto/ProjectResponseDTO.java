package com.northernarc.springdatajpademo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class ProjectResponseDTO {

    private Long id;

    @NotBlank
    private String name;

    @Valid
    private List<EmployeeSummaryDTO> employees;
}
