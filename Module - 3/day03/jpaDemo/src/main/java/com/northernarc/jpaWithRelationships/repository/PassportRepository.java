package com.northernarc.jpaWithRelationships.repository;

import com.northernarc.jpaWithRelationships.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
