package com.northernarc.springdatajpademo.repository;

import com.northernarc.springdatajpademo.dto.EmployeeResponseDTO;
import com.northernarc.springdatajpademo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(""" 
SELECT new com.northernarc.springdatajpademo.dto.EmployeeResponseDTO(e.id,e.name,e.dept)
FROM Employee e WHERE e.dept= :dept
""")
    List<EmployeeResponseDTO> getAllEmployees(@Param("dept") String dept);
}
