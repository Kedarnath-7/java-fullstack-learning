package com.northernarc.loanmanagement.controller;

import com.northernarc.loanmanagement.dto.ApiMessageDTO;
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
import com.northernarc.loanmanagement.exceptions.LoanProductNotFoundException;
import com.northernarc.loanmanagement.model.LoanProduct;
import com.northernarc.loanmanagement.repository.LoanProductRepository;
import com.northernarc.loanmanagement.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Loan Management API", description = "Secure APIs for loan operations, applications, repayments, and analytics")
public class LoanController {

    private final LoanService loanService;
    private final LoanProductRepository loanProductRepository;

    public LoanController(LoanService loanService, LoanProductRepository loanProductRepository) {
        this.loanService = loanService;
        this.loanProductRepository = loanProductRepository;
    }

    @Operation(summary = "Create customer")
    @PostMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createCustomer(request));
    }

    @Operation(summary = "Get all customers")
    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return ResponseEntity.ok(loanService.getCustomers());
    }

    @Operation(summary = "Get customer by ID")
    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getCustomer(id));
    }

    @Operation(summary = "Update customer")
    @PutMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,
                                                              @Valid @RequestBody CustomerRequestDTO request) {
        return ResponseEntity.ok(loanService.updateCustomer(id, request));
    }

    @Operation(summary = "Delete customer")
    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        loanService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create loan product")
    @PostMapping("/loan-products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<LoanProductResponseDTO> createLoanProduct(@Valid @RequestBody LoanProductRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoanProduct(request));
    }

    @Operation(summary = "Update loan product")
    @PutMapping("/loan-products/{loanCode}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<LoanProductResponseDTO> updateLoanProduct(@PathVariable String loanCode,
                                                                    @Valid @RequestBody LoanProductRequestDTO request) {
        return ResponseEntity.ok(loanService.updateLoanProduct(loanCode, request));
    }

    @Operation(summary = "Get loan products with pagination and default sorting by dailyPenaltyRate DESC")
    @GetMapping("/loan-products")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<Page<LoanProductResponseDTO>> getLoanProducts(Pageable pageable) {
        return ResponseEntity.ok(loanService.getLoanProducts(pageable));
    }

    @Operation(summary = "Delete loan product (ADMIN only)")
    @DeleteMapping("/loan-products/{loanCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLoanProduct(@PathVariable String loanCode) {
        loanService.deleteLoanProduct(loanCode);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create loan application/account")
    @PostMapping("/loan-accounts")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<LoanAccountResponseDTO> createLoanAccount(@Valid @RequestBody LoanAccountRequestDTO request,
                                                                    Authentication authentication) {
        boolean privileged = isPrivileged(authentication);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoanAccount(request, email, privileged));
    }

    @Operation(summary = "Update loan account/application")
    @PutMapping("/loan-accounts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<LoanAccountResponseDTO> updateLoanAccount(@PathVariable Long id,
                                                                    @Valid @RequestBody LoanAccountRequestDTO request,
                                                                    Authentication authentication) {
        boolean privileged = isPrivileged(authentication);
        String email = authentication.getName();
        return ResponseEntity.ok(loanService.updateLoanAccount(id, request, email, privileged));
    }

    @Operation(summary = "Get loan accounts (USER gets own, ADMIN/MANAGER get all)")
    @GetMapping("/loan-accounts")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<List<LoanAccountResponseDTO>> getLoanAccounts(Authentication authentication) {
        boolean privileged = isPrivileged(authentication);
        String email = authentication.getName();
        return ResponseEntity.ok(loanService.getLoanAccounts(email, privileged));
    }

    @Operation(summary = "Get one loan account detail (USER gets own, ADMIN/MANAGER get any)")
    @GetMapping("/loan-accounts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<LoanAccountResponseDTO> getLoanAccount(@PathVariable Long id, Authentication authentication) {
        boolean privileged = isPrivileged(authentication);
        String email = authentication.getName();
        return ResponseEntity.ok(loanService.getLoanAccountDetails(id, email, privileged));
    }

    @Operation(summary = "Delete loan account (ADMIN only)")
    @DeleteMapping("/loan-accounts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLoanAccount(@PathVariable Long id) {
        loanService.deleteLoanAccount(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Close loan account (MANAGER only)")
    @PutMapping("/loan-accounts/{id}/close")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiMessageDTO> closeLoan(@PathVariable Long id) {
        loanService.closeLoan(id);
        return ResponseEntity.ok(new ApiMessageDTO("Loan account closed"));
    }

    @Operation(summary = "View pending loan applications (UNDERWRITER only)")
    @GetMapping("/loan-applications")
    @PreAuthorize("hasRole('UNDERWRITER')")
    public ResponseEntity<List<LoanAccountResponseDTO>> getLoanApplications() {
        return ResponseEntity.ok(loanService.getLoanApplications());
    }

    @Operation(summary = "Approve loan application (UNDERWRITER only)")
    @PutMapping("/loan-applications/{id}/approve")
    @PreAuthorize("hasRole('UNDERWRITER')")
    public ResponseEntity<ApiMessageDTO> approveLoanApplication(@PathVariable Long id) {
        loanService.approveLoan(id);
        return ResponseEntity.ok(new ApiMessageDTO("Loan application approved"));
    }

    @Operation(summary = "Reject loan application (UNDERWRITER only)")
    @PutMapping("/loan-applications/{id}/reject")
    @PreAuthorize("hasRole('UNDERWRITER')")
    public ResponseEntity<ApiMessageDTO> rejectLoanApplication(@PathVariable Long id) {
        loanService.rejectLoan(id);
        return ResponseEntity.ok(new ApiMessageDTO("Loan application rejected"));
    }

    @Operation(summary = "Increase penalty rates by loan type (MANAGER only)")
    @PutMapping("/penalty-rates/increase")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiMessageDTO> increasePenaltyRates(@RequestParam String loanType,
                                                               @RequestParam Double amount) {
        int updated = loanService.increasePenaltyRates(loanType, amount);
        return ResponseEntity.ok(new ApiMessageDTO("Updated loan products: " + updated));
    }

    @Operation(summary = "Increase penalty rate for a specific product's loan type (MANAGER only)")
    @PutMapping("/loan-products/{loanCode}/penalty-rate")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiMessageDTO> updatePenaltyRate(@PathVariable String loanCode,
                                                            @RequestParam Double amount) {
        LoanProduct product = loanProductRepository.findById(loanCode)
                .orElseThrow(() -> new LoanProductNotFoundException("Loan product not found with code: " + loanCode));
        int updated = loanService.increasePenaltyRates(product.getLoanType().name(), amount);
        return ResponseEntity.ok(new ApiMessageDTO("Updated loan products: " + updated));
    }

    @Operation(summary = "Record EMI payment (USER only)")
    @PostMapping("/emi-payments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmiPaymentResponseDTO> makeEmiPayment(@Valid @RequestBody EmiPaymentDTO emiPaymentDTO) {
        EmiPaymentResponseDTO payment = loanService.makeEmiPayment(emiPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @Operation(summary = "Get customer summary DTO")
    @GetMapping("/customers/{id}/summary")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<CustomerSummaryDTO> getCustomerSummary(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getCustomerSummary(id));
    }

    @Operation(summary = "Get dashboard analytics")
    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<DashboardDTO> getDashboard() {
        return ResponseEntity.ok(loanService.getDashboard());
    }

    private boolean isPrivileged(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN") || role.equals("ROLE_MANAGER"));
    }
}