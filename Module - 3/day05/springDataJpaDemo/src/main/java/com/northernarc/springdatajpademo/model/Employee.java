package com.northernarc.springdatajpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
//@Setter===============||
//@Getter               || ---> same as @Data
//@NoArgsConstructor    || --->
//@AllArgsConstructor===||

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String dept;

    @ManyToMany(mappedBy = "employeeList")
    @JsonIgnore
    private List<Project> projectList;

}
