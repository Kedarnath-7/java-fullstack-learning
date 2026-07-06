package com.northernarc.loanmanagement.service;

import com.northernarc.loanmanagement.dto.CustomerRequestDTO;
import com.northernarc.loanmanagement.dto.CustomerResponseDTO;
import com.northernarc.loanmanagement.dto.CustomerSummaryDTO;
import com.northernarc.loanmanagement.dto.DashboardDTO;
import com.northernarc.loanmanagement.dto.EmiPaymentDTO;
import com.northernarc.loanmanagement.dto.EmiPaymentResponseDTO;
import com.northernarc.loanmanagement.dto.LoanAccountRequestDTO;
import com.northernarc.loanmanagement.dto.LoanAccountResponseDTO;
import com.northernarc.loanmanagement.dto.LoanProductRequestDTO;
import com.northernarc.loanmanagement.dto.LoanProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {

    // Customer use cases
    CustomerResponseDTO createCustomer(CustomerRequestDTO request);

    List<CustomerResponseDTO> getCustomers();

    CustomerResponseDTO getCustomer(Long customerId);

    CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO request);

    void deleteCustomer(Long customerId);

    // Loan product use cases
    LoanProductResponseDTO createLoanProduct(LoanProductRequestDTO request);

    LoanProductResponseDTO updateLoanProduct(String loanCode, LoanProductRequestDTO request);

    Page<LoanProductResponseDTO> getLoanProducts(Pageable pageable);

    void deleteLoanProduct(String loanCode);

    int increasePenaltyRates(String loanType, Double amount);

    // Loan account / application use cases
    LoanAccountResponseDTO createLoanAccount(LoanAccountRequestDTO request, String authenticatedEmail, boolean privilegedUser);

    LoanAccountResponseDTO updateLoanAccount(Long loanAccountId, LoanAccountRequestDTO request, String authenticatedEmail, boolean privilegedUser);

    List<LoanAccountResponseDTO> getLoanAccounts(String email, boolean privilegedUser);

    LoanAccountResponseDTO getLoanAccountDetails(Long loanAccountId, String email, boolean privilegedUser);

    List<LoanAccountResponseDTO> getLoanApplications();

    void approveLoan(Long loanAccountId);

    void rejectLoan(Long loanAccountId);

    void closeLoan(Long loanAccountId);

    void deleteLoanAccount(Long loanAccountId);

    // EMI use cases
    EmiPaymentResponseDTO makeEmiPayment(EmiPaymentDTO emiPaymentDTO);

    // Reporting use cases
    CustomerSummaryDTO getCustomerSummary(Long customerId);

    DashboardDTO getDashboard();
}