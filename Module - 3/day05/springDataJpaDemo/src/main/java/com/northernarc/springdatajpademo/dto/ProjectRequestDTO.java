package com.northernarc.springdatajpademo.dto;

import com.northernarc.springdatajpademo.model.Employee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectRequestDTO {

    @NotBlank
    private String name;

    @Valid
    private List<Employee> employeeList;

}
