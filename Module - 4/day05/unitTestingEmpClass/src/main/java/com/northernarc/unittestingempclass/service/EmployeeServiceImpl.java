package com.northernarc.unittestingempclass.service;

import com.northernarc.unittestingempclass.dto.EmployeeRequestDTO;
import com.northernarc.unittestingempclass.dto.EmployeeResponseDTO;
import com.northernarc.unittestingempclass.exceptions.EmployeeNotFoundException;
import com.northernarc.unittestingempclass.model.Employee;
import com.northernarc.unittestingempclass.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO findById(Long id) {
        return employeeRepository.findById(id).map((emp)->mapToResponse(emp)).orElseThrow(()->new EmployeeNotFoundException("Employee not found..."));
    }

    private EmployeeResponseDTO mapToResponse(Employee emp) {
        return new EmployeeResponseDTO(emp.getId(), emp.getName(), emp.getSalary());
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = new Employee();
        emp.setName(employeeRequestDTO.getName());
        emp.setSalary(employeeRequestDTO.getSalary());
        return mapToResponse(employeeRepository.save(emp));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public void updateById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found..."));
        emp.setName(employeeRequestDTO.getName());
        emp.setSalary(employeeRequestDTO.getSalary());
        employeeRepository.save(emp);
    }
}
