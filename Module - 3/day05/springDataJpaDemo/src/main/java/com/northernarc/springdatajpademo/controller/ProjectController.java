package com.northernarc.springdatajpademo.controller;

import com.northernarc.springdatajpademo.dto.ProjectRequestDTO;
import com.northernarc.springdatajpademo.dto.ProjectResponseDTO;
import com.northernarc.springdatajpademo.model.Employee;
import com.northernarc.springdatajpademo.model.Project;
import com.northernarc.springdatajpademo.repository.ProjectRepository;
import com.northernarc.springdatajpademo.service.ProjectService;
import com.northernarc.springdatajpademo.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

//    @GetMapping
//    public ResponseEntity<List<Project>> getAll(){
//        return ResponseEntity.ok(projectService.getAllProject());
//    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAll(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Project> getById(@PathVariable Long id){
//        return ResponseEntity.ok(projectService.findById(id));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.findById(id));
    }

//    @PostMapping("/add")
//    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project){
//        return new ResponseEntity<>(projectService.addProject(project), HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public ResponseEntity<ProjectResponseDTO> addProject(@Valid @RequestBody ProjectRequestDTO requestDTO){
        return new ResponseEntity<>(projectService.addProject(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/assign/{pid}/{eid}")
    public ResponseEntity assignProject(@PathVariable Long pid, @PathVariable Long eid){
        projectService.assignProjectToEmployee(pid, eid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employeesByProject/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByProjectName(@PathVariable String name){
        return new ResponseEntity<>(projectService.getEmployeesByProjectName(name), HttpStatus.OK);
    }

    @DeleteMapping("remove/{pid}/employees/{eid}")
    public ResponseEntity removeEmployeeFromProject(@PathVariable Long pid, @PathVariable Long eid) {
        projectService.removeEmployeeFromProject(pid, eid);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody Project project) {

        projectService.updateById(id, project);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProjects() {
        projectService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
