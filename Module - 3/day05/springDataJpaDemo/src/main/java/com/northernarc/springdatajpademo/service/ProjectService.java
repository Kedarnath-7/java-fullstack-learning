package com.northernarc.springdatajpademo.service;

import com.northernarc.springdatajpademo.dto.EmployeeResponseDTO;
import com.northernarc.springdatajpademo.dto.ProjectRequestDTO;
import com.northernarc.springdatajpademo.dto.ProjectResponseDTO;
import com.northernarc.springdatajpademo.model.Employee;
import com.northernarc.springdatajpademo.model.Project;

import java.util.List;

public interface ProjectService {
    ProjectResponseDTO addProject(ProjectRequestDTO requestDTO);
    List<ProjectResponseDTO> getAllProject();
    ProjectResponseDTO findById(Long id);
    void deleteById(Long id);
    void deleteAll();
    void updateById(Long id, Project project);
    void assignProjectToEmployee(Long pid, Long eid);
    List<Employee> getEmployeesByProjectName(String name);
    void removeEmployeeFromProject(Long pid, Long eid);
}
