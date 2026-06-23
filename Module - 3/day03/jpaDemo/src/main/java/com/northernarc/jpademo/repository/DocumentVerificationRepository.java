package com.northernarc.jpademo.repository;

import com.northernarc.jpademo.model.DocumentVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentVerificationRepository extends JpaRepository<DocumentVerification, Integer> {
}
