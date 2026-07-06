package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.EmiPayment;
import com.northernarc.loanmanagement.model.PaymentType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmiPaymentRepository extends JpaRepository<EmiPayment, Long> {

    List<EmiPayment> findByPaymentType(PaymentType paymentType);

    @Query("SELECT ep FROM EmiPayment ep WHERE UPPER(CAST(ep.paymentType as string)) = UPPER(:paymentType)")
    List<EmiPayment> findByPaymentType(@Param("paymentType") String paymentType);

    @Query("SELECT ep FROM EmiPayment ep ORDER BY ep.paymentDate DESC, ep.emiPaymentId DESC")
    List<EmiPayment> findLatestEmiPayment(Pageable pageable);

    @Query("SELECT COALESCE(SUM(ep.penaltyPaid), 0.0) FROM EmiPayment ep")
    Double findTotalPenaltyCollected();
}
