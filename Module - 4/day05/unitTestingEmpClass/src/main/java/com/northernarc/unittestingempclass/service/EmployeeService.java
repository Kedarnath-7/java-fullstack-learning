package com.northernarc.unittestingempclass.service;

import com.northernarc.unittestingempclass.dto.EmployeeRequestDTO;
import com.northernarc.unittestingempclass.dto.EmployeeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO findById(Long id);
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);
    void deleteById(Long id);
    void deleteAll();
    List<EmployeeResponseDTO> findAll();
    void updateById(Long id, EmployeeRequestDTO employeeRequestDTO);
}
