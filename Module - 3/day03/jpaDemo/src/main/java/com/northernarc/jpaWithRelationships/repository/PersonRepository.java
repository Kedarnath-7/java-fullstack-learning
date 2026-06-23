package com.northernarc.jpaWithRelationships.repository;

import com.northernarc.jpaWithRelationships.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
