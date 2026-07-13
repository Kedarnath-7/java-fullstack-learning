package com.northernarc.springdatajpademo.service;

import com.northernarc.springdatajpademo.dto.EmployeeRequestDTO;
import com.northernarc.springdatajpademo.dto.EmployeeResponseDTO;
import com.northernarc.springdatajpademo.dto.ProjectSummaryDTO;
import com.northernarc.springdatajpademo.exceptions.EmployeeNotFound;
import com.northernarc.springdatajpademo.model.Employee;
import com.northernarc.springdatajpademo.model.Project;
import com.northernarc.springdatajpademo.repository.EmployeeRepository;
import com.northernarc.springdatajpademo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = new Employee();
        emp.setName(employeeRequestDTO.getName());
        emp.setEmail(employeeRequestDTO.getEmail());
        emp.setDept(employeeRequestDTO.getDept());
        return mapToResponse(employeeRepository.save(emp));
    }

    @Override
    public EmployeeResponseDTO findById(Long id) {
        return employeeRepository.findById(id).map((this::mapToResponse)).orElseThrow(()->new EmployeeNotFound("No employee found..."));
    }

    @Override
    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    private EmployeeResponseDTO mapToResponse(Employee emp) {
        return new EmployeeResponseDTO(emp.getId(), emp.getName(), emp.getDept(), emp.getProjectList().stream().map(this::mapToProjectSummary).toList());
    }

    private ProjectSummaryDTO mapToProjectSummary(Project project) {
        return new ProjectSummaryDTO(project.getId(), project.getName());
    }

    @Override
    public void updateById(Long id, Employee employee) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee not found"));
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        emp.setDept(employee.getDept());
        employeeRepository.save(emp);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }


    public List<EmployeeResponseDTO> getAllSorted() {
        return employeeRepository.findAll(Sort.by("name")
                        .ascending()
                        .and(Sort.by("dept")
                                .descending()))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<EmployeeResponseDTO> getAllByDepartment(String dept) {
        return employeeRepository.getAllEmployees(dept);
    }

    public List<EmployeeResponseDTO> getAllByPage(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page,size, Sort.by(Employee::getName)))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private Employee mapToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = new Employee();
        emp.setName(employeeRequestDTO.getName());
        emp.setDept(employeeRequestDTO.getDept());
        emp.setEmail(employeeRequestDTO.getEmail());
        return emp;
    }


}
