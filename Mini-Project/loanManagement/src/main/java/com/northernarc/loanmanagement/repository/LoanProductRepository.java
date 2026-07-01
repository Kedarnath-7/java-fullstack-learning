package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.LoanProduct;
import com.northernarc.loanmanagement.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {

    // JPL derived queries
    List<LoanProduct> findByLoanType(LoanType loanType);
    List<LoanProduct> findByDailyPenaltyRateGreaterThan(Double rate);

    // JPQL queries

}
