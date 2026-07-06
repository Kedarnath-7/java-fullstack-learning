package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.dto.CustomerSummaryDTO;
import com.northernarc.loanmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByBranch(String branch);

    Optional<Customer> findByEmail(String email);

    @Query("SELECT c FROM Customer c JOIN c.loanAccounts la GROUP BY c HAVING COUNT(la) > :minLoan")
    List<Customer> findPremiumBorrowers(@Param("minLoan") long minLoan);

    @Query("SELECT c.branch, COALESCE(SUM(ep.penaltyPaid), 0.0) FROM Customer c " +
            "JOIN c.loanAccounts la JOIN la.emiPayments ep GROUP BY c.branch")
    List<Object[]> findTotalPenaltyCollectedPerBranch();

    @Query("SELECT c FROM Customer c JOIN c.loanAccounts la " +
            "GROUP BY c HAVING COUNT(DISTINCT la.loanProduct.loanType) > 1")
    List<Customer> findCustomersUsingMultipleLoanTypes();

    @Query("SELECT new com.northernarc.loanmanagement.dto.CustomerSummaryDTO(" +
            "c.customerName, c.branch, " +
            "(SELECT CAST(COUNT(la1.loanAccountId) as int) FROM LoanAccount la1 WHERE la1.customer = c), " +
            "(SELECT COALESCE(SUM(la2.loanAmount), 0.0) FROM LoanAccount la2 WHERE la2.customer = c), " +
            "(SELECT COALESCE(SUM(ep.penaltyPaid), 0.0) FROM EmiPayment ep WHERE ep.loanAccount.customer = c)) " +
            "FROM Customer c WHERE c.customerId = :customerId")
    Optional<CustomerSummaryDTO> findCustomerSummaryById(@Param("customerId") Long customerId);
}
