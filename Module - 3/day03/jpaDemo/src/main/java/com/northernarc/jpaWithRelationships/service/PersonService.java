package com.northernarc.jpaWithRelationships.service;

import com.northernarc.jpaWithRelationships.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonService {
    Person addPerson(Person person);
    List<Person> getAll();
}
