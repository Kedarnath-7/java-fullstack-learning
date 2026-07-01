package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.LoanAccount;
import com.northernarc.loanmanagement.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {
    List<LoanAccount> findByLoanStatus(LoanStatus loanStatus);
}
