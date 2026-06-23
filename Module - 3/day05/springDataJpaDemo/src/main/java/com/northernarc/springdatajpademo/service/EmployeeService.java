package com.northernarc.springdatajpademo.service;

import com.northernarc.springdatajpademo.dto.EmployeeRequestDTO;
import com.northernarc.springdatajpademo.dto.EmployeeResponseDTO;
import com.northernarc.springdatajpademo.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO findById(Long id);
    List<EmployeeResponseDTO> findAll();
    void updateById(Long id, Employee employee);
    void deleteById(Long id);
    void deleteAll();

    List<EmployeeResponseDTO> getAllByDepartment(String dept);
    List<EmployeeResponseDTO> getAllByPage(int page, int size);
}
