package com.northernarc.jpaWithRelationships.service;

import com.northernarc.jpaWithRelationships.model.Passport;

import java.util.List;

public interface PassportService {

    Passport addPassport(Passport passport);
    List<Passport> getAll();
}
