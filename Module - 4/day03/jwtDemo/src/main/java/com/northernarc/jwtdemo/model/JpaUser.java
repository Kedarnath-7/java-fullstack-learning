package com.northernarc.jwtdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class JpaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;
}
