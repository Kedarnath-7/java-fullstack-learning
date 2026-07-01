package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // JPA derived query
    List<Customer> findByBranch(String branch);

    @Query("select c from Customer c join c.loanAccounts la group by c having count(la) > :minLoan")
    List<Customer> findPremiumBorrowers(@Param("minLoan") long minLoan);



}
