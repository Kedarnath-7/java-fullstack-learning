package com.northernarc.jpaWithRelationships.service;

import com.northernarc.jpaWithRelationships.model.Passport;
import com.northernarc.jpaWithRelationships.model.Person;
import com.northernarc.jpaWithRelationships.repository.PassportRepository;
import com.northernarc.jpaWithRelationships.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService{
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private PersonRepository personRepository;
    public Passport addPassport(Passport passport){
        Person person=passport.getPerson();
        personRepository.save(person);
        return passportRepository.save(passport);
    }
    public List<Passport> getAll(){
        return passportRepository.findAll();
    }
}