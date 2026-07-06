package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.LoanProduct;
import com.northernarc.loanmanagement.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LoanProductRepository extends JpaRepository<LoanProduct, String> {

    @Query("SELECT lp FROM LoanProduct lp WHERE UPPER(CAST(lp.loanType as string)) = UPPER(:loanType)")
    List<LoanProduct> findByLoanType(@Param("loanType") String loanType);

    default List<LoanProduct> findByLoanType(LoanType loanType) {
        return findByLoanType(loanType.name());
    }

    List<LoanProduct> findByDailyPenaltyRateGreaterThan(Double rate);

    default List<LoanProduct> findByDailyPenaltyRateGreaterThan(double rate) {
        return findByDailyPenaltyRateGreaterThan(Double.valueOf(rate));
    }

    @Query("SELECT lp FROM LoanProduct lp WHERE lp NOT IN " +
            "(SELECT DISTINCT la.loanProduct FROM LoanAccount la WHERE la.loanStatus = 'OVERDUE')")
    List<LoanProduct> findLoanProductsWithNoOverdueHistory();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE LoanProduct lp SET lp.dailyPenaltyRate = lp.dailyPenaltyRate + :amount " +
            "WHERE UPPER(CAST(lp.loanType as string)) = UPPER(:loanType)")
    int increaseDailyPenaltyRates(@Param("loanType") String loanType, @Param("amount") Double amount);
}
