package com.northernarc.weeklyassignment3.repository;

import com.northernarc.weeklyassignment3.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, String> {
}