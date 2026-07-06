package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.LoanAccount;
import com.northernarc.loanmanagement.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {

    List<LoanAccount> findByLoanStatus(LoanStatus loanStatus);

    List<LoanAccount> findByCustomerEmail(String email);

    Optional<LoanAccount> findByLoanAccountIdAndCustomerEmail(Long loanAccountId, String email);

    @Query("SELECT COALESCE(SUM(la.loanAmount), 0.0) FROM LoanAccount la")
    Double findTotalLoanAmountDisbursed();

    @Query("SELECT c.branch, COALESCE(SUM(la.loanAmount), 0.0) FROM LoanAccount la " +
            "JOIN la.customer c GROUP BY c.branch ORDER BY SUM(la.loanAmount) DESC")
    List<Object[]> findTotalLoanAmountPerBranch();

    @Query("SELECT c.customerName, COALESCE(SUM(la.loanAmount), 0.0) FROM LoanAccount la " +
            "JOIN la.customer c GROUP BY c.customerName ORDER BY SUM(la.loanAmount) DESC")
    List<Object[]> findHighestLoanCustomer();

    @Query("SELECT la FROM LoanAccount la " +
            "JOIN FETCH la.customer c JOIN FETCH la.loanProduct lp " +
            "LEFT JOIN FETCH la.emiPayments ep WHERE la.loanAccountId = :loanAccountId")
    Optional<LoanAccount> findDetailsById(@Param("loanAccountId") Long loanAccountId);

    @Query("SELECT la FROM LoanAccount la JOIN FETCH la.customer c JOIN FETCH la.loanProduct lp " +
            "WHERE la.loanStatus = :status ORDER BY la.loanStartDate DESC")
    List<LoanAccount> findApplicationsByStatus(@Param("status") LoanStatus status);
}