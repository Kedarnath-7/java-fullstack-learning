package com.northernarc.jpaWithRelationships.controller;

import com.northernarc.jpaWithRelationships.model.Passport;
import com.northernarc.jpaWithRelationships.service.PassportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportController {
    @Autowired
    PassportServiceImpl passportService;
    @PostMapping
    public Passport addPassport(@RequestBody Passport passport){
        return passportService.addPassport(passport);
    }
    @GetMapping
    public List<Passport> getAll(){
        return passportService.getAll();
    }
}

