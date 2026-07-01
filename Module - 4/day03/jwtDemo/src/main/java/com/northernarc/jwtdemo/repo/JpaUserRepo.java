package com.northernarc.jwtdemo.repo;

import com.northernarc.jwtdemo.model.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepo extends JpaRepository<JpaUser, Long> {
    public JpaUser findByUsername(String username);
}
