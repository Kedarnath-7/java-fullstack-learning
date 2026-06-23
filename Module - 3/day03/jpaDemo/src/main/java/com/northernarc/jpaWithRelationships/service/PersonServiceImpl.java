package com.northernarc.jpaWithRelationships.service;

import com.northernarc.jpaWithRelationships.model.Passport;
import com.northernarc.jpaWithRelationships.model.Person;
import com.northernarc.jpaWithRelationships.repository.PassportRepository;
import com.northernarc.jpaWithRelationships.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;
    public Person addPerson(Person person){
        return personRepository.save(person);
    }
    public List<Person> getAll(){
        return personRepository.findAll();
    }
}