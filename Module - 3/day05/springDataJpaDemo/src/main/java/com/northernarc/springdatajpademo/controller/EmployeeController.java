package com.northernarc.springdatajpademo.controller;

import com.northernarc.springdatajpademo.dto.EmployeeRequestDTO;
import com.northernarc.springdatajpademo.dto.EmployeeResponseDTO;
import com.northernarc.springdatajpademo.model.Employee;
import com.northernarc.springdatajpademo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/dept/{dept}")
    public List<EmployeeResponseDTO> getAllEmployeesByDept(@PathVariable String dept){
        return employeeService.getAllByDepartment(dept);
    }

    @GetMapping("/page/{page}/{size}")
    public List<EmployeeResponseDTO> getAllEmployeesByPage(@PathVariable int page, @PathVariable int size){
        return employeeService.getAllByPage(page,size);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {

        return new ResponseEntity<>(employeeService.addEmployee(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {

        employeeService.updateById(id, employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployees() {
        employeeService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
