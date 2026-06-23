package com.northernarc.springdatajpademo.repository;

import com.northernarc.springdatajpademo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
