package com.northernarc.loanmanagement.repository;

import com.northernarc.loanmanagement.model.EmiPayment;
import com.northernarc.loanmanagement.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmiPaymentRepository extends JpaRepository<EmiPayment, Long> {
    // JPA derived query
    List<EmiPayment> findByPaymentType(PaymentType paymentType);
}
