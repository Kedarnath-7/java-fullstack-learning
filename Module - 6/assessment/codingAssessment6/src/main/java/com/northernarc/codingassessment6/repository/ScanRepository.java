package com.northernarc.codingassessment6.repository;

import com.hackerrank.api.model.Scan;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, Long> {

	List<Scan> findByDomainName(String domainName, Sort by);

	
}
