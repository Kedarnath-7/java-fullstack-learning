package com.northernarc.springdatajpademo.service;

import com.northernarc.springdatajpademo.dto.EmployeeSummaryDTO;
import com.northernarc.springdatajpademo.dto.ProjectRequestDTO;
import com.northernarc.springdatajpademo.dto.ProjectResponseDTO;
import com.northernarc.springdatajpademo.exceptions.EmployeeNotFound;
import com.northernarc.springdatajpademo.exceptions.ProjectNotFound;
import com.northernarc.springdatajpademo.model.Employee;
import com.northernarc.springdatajpademo.model.Project;
import com.northernarc.springdatajpademo.repository.EmployeeRepository;
import com.northernarc.springdatajpademo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ProjectResponseDTO addProject(ProjectRequestDTO requestDTO) {
        Project project = new Project();
        project.setName(requestDTO.getName());
        project.setEmployeeList(requestDTO.getEmployeeList());
        return mapToResponse(projectRepository.save(project));
    }

    private ProjectResponseDTO mapToResponse(Project project) {
        return new ProjectResponseDTO(project.getId(), project.getName(), project.getEmployeeList().stream().map(this::mapToEmployeeSummary).toList());
    }

    private EmployeeSummaryDTO mapToEmployeeSummary(Employee employee){
        return new EmployeeSummaryDTO(employee.getId(), employee.getName());
    }

    @Override
    public List<ProjectResponseDTO> getAllProject() {
        return projectRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public ProjectResponseDTO findById(Long id) {
        return projectRepository.findById(id).map(this::mapToResponse).orElseThrow(()->new ProjectNotFound("No project found.."));
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        projectRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, Project project) {
        Project project1 = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFound("no project found..."));
        project1.setName(project.getName());
        project1.setEmployeeList(project.getEmployeeList());
        projectRepository.save(project1);
    }


    @Override
    public void assignProjectToEmployee(Long pid, Long eid) {
        Project p = projectRepository.findById(pid).orElseThrow(()-> new ProjectNotFound("No project found..."));
        p.getEmployeeList().add(employeeRepository.findById(eid).orElseThrow(()-> new EmployeeNotFound("No employee found...")));
        projectRepository.save(p);
    }

    @Override
    public List<Employee> getEmployeesByProjectName(String name){
        Project p = projectRepository.findByName(name);
        if(p == null){
            throw new ProjectNotFound("No project found...");
        }
        return p.getEmployeeList();
    }



    @Override
    public void removeEmployeeFromProject(Long pid, Long eid) {
        Project project = projectRepository.findById(pid).orElseThrow(() -> new ProjectNotFound("No project found"));

        Employee employee = employeeRepository.findById(eid).orElseThrow(() -> new EmployeeNotFound("No employee found"));

        project.getEmployeeList().remove(employee);
        employee.getProjectList().remove(project);

        projectRepository.save(project);
        employeeRepository.save(employee);
    }


}
