package com.northernarc.springdatajpademo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String dept;

    @Valid
    private List<ProjectSummaryDTO> projects;

    public EmployeeResponseDTO(Long id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

}
