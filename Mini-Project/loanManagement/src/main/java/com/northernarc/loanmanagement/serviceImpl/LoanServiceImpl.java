package com.northernarc.loanmanagement.serviceimpl;

import com.northernarc.loanmanagement.dto.CustomerRequestDTO;
import com.northernarc.loanmanagement.dto.CustomerResponseDTO;
import com.northernarc.loanmanagement.dto.CustomerSummaryDTO;
import com.northernarc.loanmanagement.dto.DashboardDTO;
import com.northernarc.loanmanagement.dto.EmiCalculatorRequestDTO;
import com.northernarc.loanmanagement.dto.EmiCalculatorResponseDTO;
import com.northernarc.loanmanagement.dto.EmiPaymentDTO;
import com.northernarc.loanmanagement.dto.EmiPaymentResponseDTO;
import com.northernarc.loanmanagement.dto.LoanAccountRequestDTO;
import com.northernarc.loanmanagement.dto.LoanAccountResponseDTO;
import com.northernarc.loanmanagement.dto.LoanProductRequestDTO;
import com.northernarc.loanmanagement.dto.LoanProductResponseDTO;
import com.northernarc.loanmanagement.exceptions.CustomerNotFoundException;
import com.northernarc.loanmanagement.exceptions.LoanAccountNotFoundException;
import com.northernarc.loanmanagement.exceptions.LoanProductNotFoundException;
import com.northernarc.loanmanagement.exceptions.ValidationException;
import com.northernarc.loanmanagement.model.Customer;
import com.northernarc.loanmanagement.model.EmiPayment;
import com.northernarc.loanmanagement.model.LoanAccount;
import com.northernarc.loanmanagement.model.LoanProduct;
import com.northernarc.loanmanagement.model.LoanStatus;
import com.northernarc.loanmanagement.model.LoanType;
import com.northernarc.loanmanagement.model.PaymentType;
import com.northernarc.loanmanagement.model.UserRole;
import com.northernarc.loanmanagement.repository.CustomerRepository;
import com.northernarc.loanmanagement.repository.EmiPaymentRepository;
import com.northernarc.loanmanagement.repository.LoanAccountRepository;
import com.northernarc.loanmanagement.repository.LoanProductRepository;
import com.northernarc.loanmanagement.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
@Slf4j
public class LoanServiceImpl implements LoanService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MAX_SIZE = 200;

    private final CustomerRepository customerRepository;
    private final LoanProductRepository loanProductRepository;
    private final LoanAccountRepository loanAccountRepository;
    private final EmiPaymentRepository emiPaymentRepository;
    private final PasswordEncoder passwordEncoder;

    public LoanServiceImpl(CustomerRepository customerRepository,
                           LoanProductRepository loanProductRepository,
                           LoanAccountRepository loanAccountRepository,
                           EmiPaymentRepository emiPaymentRepository,
                           PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.loanProductRepository = loanProductRepository;
        this.loanAccountRepository = loanAccountRepository;
        this.emiPaymentRepository = emiPaymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
        if (request == null) {
            throw new ValidationException("Customer request cannot be null");
        }

        customerRepository.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new ValidationException("Customer already exists with email: " + request.getEmail());
        });

        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName().trim());
        customer.setEmail(request.getEmail().trim().toLowerCase());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setBranch(request.getBranch().trim());
        customer.setRole(parseUserRole(request.getRole()));

        Customer saved = customerRepository.save(customer);
        log.info("Created customer id={} email={} role={}", saved.getCustomerId(), saved.getEmail(), saved.getRole());
        return toCustomerResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomers() {
        return customerRepository.findAll().stream().map(this::toCustomerResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
        return toCustomerResponse(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO request) {
        if (request == null) {
            throw new ValidationException("Customer request cannot be null");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

        String normalizedEmail = request.getEmail().trim().toLowerCase();
        customerRepository.findByEmail(normalizedEmail)
                .filter(existing -> !existing.getCustomerId().equals(customerId))
                .ifPresent(existing -> {
                    throw new ValidationException("Email already used by another customer: " + normalizedEmail);
                });

        customer.setCustomerName(request.getCustomerName().trim());
        customer.setEmail(normalizedEmail);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setBranch(request.getBranch().trim());
        customer.setRole(parseUserRole(request.getRole()));

        Customer saved = customerRepository.save(customer);
        log.info("Updated customer id={} email={}", saved.getCustomerId(), saved.getEmail());
        return toCustomerResponse(saved);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
        customerRepository.delete(customer);
        log.info("Deleted customer id={}", customerId);
    }

    @Override
    public LoanProductResponseDTO createLoanProduct(LoanProductRequestDTO request) {
        if (request == null) {
            throw new ValidationException("Loan product request cannot be null");
        }

        String loanCode = request.getLoanCode().trim();
        if (loanProductRepository.existsById(loanCode)) {
            throw new ValidationException("Loan product already exists with code: " + loanCode);
        }

        LoanProduct product = new LoanProduct();
        product.setLoanCode(loanCode);
        product.setLoanName(request.getLoanName().trim());
        product.setLoanType(parseLoanType(request.getLoanType()));
        product.setInterestRate(request.getInterestRate());
        product.setDailyPenaltyRate(request.getDailyPenaltyRate());

        LoanProduct saved = loanProductRepository.save(product);
        log.info("Created loan product code={} type={}", saved.getLoanCode(), saved.getLoanType());
        return toLoanProductResponse(saved);
    }

    @Override
    public LoanProductResponseDTO updateLoanProduct(String loanCode, LoanProductRequestDTO request) {
        if (request == null) {
            throw new ValidationException("Loan product request cannot be null");
        }

        LoanProduct product = loanProductRepository.findById(loanCode)
                .orElseThrow(() -> new LoanProductNotFoundException("Loan product not found with code: " + loanCode));

        product.setLoanName(request.getLoanName().trim());
        product.setLoanType(parseLoanType(request.getLoanType()));
        product.setInterestRate(request.getInterestRate());
        product.setDailyPenaltyRate(request.getDailyPenaltyRate());

        LoanProduct saved = loanProductRepository.save(product);
        log.info("Updated loan product code={}", saved.getLoanCode());
        return toLoanProductResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LoanProductResponseDTO> getLoanProducts(Pageable pageable) {
        int page = DEFAULT_PAGE;
        int size = DEFAULT_SIZE;

        if (pageable != null && pageable.isPaged()) {
            page = Math.max(pageable.getPageNumber(), DEFAULT_PAGE);
            size = pageable.getPageSize() <= 0 ? DEFAULT_SIZE : Math.min(pageable.getPageSize(), MAX_SIZE);
        }

        Pageable sortedPageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dailyPenaltyRate"));
        return loanProductRepository.findAll(sortedPageable).map(this::toLoanProductResponse);
    }

    @Override
    public void deleteLoanProduct(String loanCode) {
        LoanProduct product = loanProductRepository.findById(loanCode)
                .orElseThrow(() -> new LoanProductNotFoundException("Loan product not found with code: " + loanCode));
        loanProductRepository.delete(product);
        log.info("Deleted loan product code={}", loanCode);
    }

    @Override
    public int increasePenaltyRates(String loanType, Double amount) {
        if (loanType == null || loanType.isBlank()) {
            throw new ValidationException("Loan type is required");
        }
        if (amount == null || amount <= 0) {
            throw new ValidationException("Increase amount must be positive");
        }

        int updated = loanProductRepository.increaseDailyPenaltyRates(loanType, amount);
        if (updated == 0) {
            throw new LoanProductNotFoundException("No loan products found for loan type: " + loanType);
        }

        log.info("Increased daily penalty rates for loanType={} by amount={} updatedRows={}", loanType, amount, updated);
        return updated;
    }

    @Override
    public LoanAccountResponseDTO createLoanAccount(LoanAccountRequestDTO request, String authenticatedEmail, boolean privilegedUser) {
        if (request == null) {
            throw new ValidationException("Loan account request cannot be null");
        }

        Customer customer = resolveCustomerForAccount(request.getCustomerId(), authenticatedEmail, privilegedUser);
        LoanProduct loanProduct = loanProductRepository.findById(request.getLoanCode().trim())
                .orElseThrow(() -> new LoanProductNotFoundException("Loan product not found with code: " + request.getLoanCode()));

        LoanAccount account = new LoanAccount();
        account.setCustomer(customer);
        account.setLoanProduct(loanProduct);
        account.setLoanStartDate(request.getLoanStartDate());
        account.setEmiDueDate(request.getEmiDueDate());
        account.setLoanAmount(request.getLoanAmount());
        account.setEmiAmount(request.getEmiAmount());
        account.setLoanStatus(LoanStatus.PENDING);

        LoanAccount saved = loanAccountRepository.save(account);
        log.info("Created loan application id={} for customer={} status={}", saved.getLoanAccountId(), customer.getEmail(), saved.getLoanStatus());
        return toLoanAccountResponse(saved);
    }

    @Override
    public LoanAccountResponseDTO updateLoanAccount(Long loanAccountId,
                                                    LoanAccountRequestDTO request,
                                                    String authenticatedEmail,
                                                    boolean privilegedUser) {
        if (request == null) {
            throw new ValidationException("Loan account request cannot be null");
        }

        LoanAccount account = privilegedUser
                ? loanAccountRepository.findById(loanAccountId).orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId))
                : loanAccountRepository.findByLoanAccountIdAndCustomerEmail(loanAccountId, authenticatedEmail)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));

        if (account.getLoanStatus() == LoanStatus.CLOSED || account.getLoanStatus() == LoanStatus.REJECTED) {
            throw new ValidationException("Closed or rejected loan accounts cannot be updated");
        }

        LoanProduct loanProduct = loanProductRepository.findById(request.getLoanCode().trim())
                .orElseThrow(() -> new LoanProductNotFoundException("Loan product not found with code: " + request.getLoanCode()));

        if (privilegedUser && request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + request.getCustomerId()));
            account.setCustomer(customer);
        }

        account.setLoanProduct(loanProduct);
        account.setLoanStartDate(request.getLoanStartDate());
        account.setEmiDueDate(request.getEmiDueDate());
        account.setLoanAmount(request.getLoanAmount());
        account.setEmiAmount(request.getEmiAmount());

        LoanAccount saved = loanAccountRepository.save(account);
        log.info("Updated loan account id={}", loanAccountId);
        return toLoanAccountResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanAccountResponseDTO> getLoanAccounts(String email, boolean privilegedUser) {
        List<LoanAccount> loanAccounts = privilegedUser
                ? loanAccountRepository.findAll()
                : loanAccountRepository.findByCustomerEmail(email);

        return loanAccounts.stream().map(this::toLoanAccountResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoanAccountResponseDTO getLoanAccountDetails(Long loanAccountId, String email, boolean privilegedUser) {
        LoanAccount loanAccount = privilegedUser
                ? loanAccountRepository.findDetailsById(loanAccountId)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId))
                : loanAccountRepository.findByLoanAccountIdAndCustomerEmail(loanAccountId, email)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));

        return toLoanAccountResponse(loanAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanAccountResponseDTO> getLoanApplications() {
        return loanAccountRepository.findApplicationsByStatus(LoanStatus.PENDING)
                .stream()
                .map(this::toLoanAccountResponse)
                .toList();
    }

    @Override
    public void approveLoan(Long loanAccountId) {
        LoanAccount account = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));

        if (account.getLoanStatus() != LoanStatus.PENDING) {
            throw new ValidationException("Only PENDING applications can be approved");
        }

        account.setLoanStatus(LoanStatus.ACTIVE);
        account.setLoanCloseDate(null);
        loanAccountRepository.save(account);
        log.info("Approved loan application id={}", loanAccountId);
    }

    @Override
    public void rejectLoan(Long loanAccountId) {
        LoanAccount account = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));

        if (account.getLoanStatus() != LoanStatus.PENDING) {
            throw new ValidationException("Only PENDING applications can be rejected");
        }

        account.setLoanStatus(LoanStatus.REJECTED);
        account.setLoanCloseDate(LocalDate.now());
        loanAccountRepository.save(account);
        log.info("Rejected loan application id={}", loanAccountId);
    }

    @Override
    public void closeLoan(Long loanAccountId) {
        LoanAccount account = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));

        if (account.getLoanStatus() != LoanStatus.ACTIVE && account.getLoanStatus() != LoanStatus.OVERDUE) {
            throw new ValidationException("Only ACTIVE or OVERDUE loans can be closed");
        }

        account.setLoanStatus(LoanStatus.CLOSED);
        account.setLoanCloseDate(LocalDate.now());
        loanAccountRepository.save(account);
        log.info("Closed loan account id={}", loanAccountId);
    }

    @Override
    public void deleteLoanAccount(Long loanAccountId) {
        LoanAccount account = loanAccountRepository.findById(loanAccountId)
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + loanAccountId));
        loanAccountRepository.delete(account);
        log.info("Deleted loan account id={}", loanAccountId);
    }

    @Override
    public EmiCalculatorResponseDTO calculateEmi(EmiCalculatorRequestDTO request) {
        if (request == null) {
            throw new ValidationException("EMI calculator request cannot be null");
        }

        if (request.getLoanAmount() == null || request.getLoanAmount() <= 0) {
            throw new ValidationException("Loan amount must be positive");
        }
        if (request.getAnnualInterestRate() == null || request.getAnnualInterestRate() < 0) {
            throw new ValidationException("Annual interest rate must be zero or positive");
        }
        if (request.getTenureInMonths() == null || request.getTenureInMonths() <= 0) {
            throw new ValidationException("Tenure in months must be positive");
        }

        double principal = request.getLoanAmount();
        int tenure = request.getTenureInMonths();
        double monthlyRate = request.getAnnualInterestRate() / 1200.0;

        double emi;
        if (monthlyRate == 0.0) {
            emi = principal / tenure;
        } else {
            double ratePower = Math.pow(1 + monthlyRate, tenure);
            emi = (principal * monthlyRate * ratePower) / (ratePower - 1);
        }

        double totalPayableAmount = emi * tenure;
        double totalInterest = totalPayableAmount - principal;

        return new EmiCalculatorResponseDTO(
                roundCurrency(principal),
                request.getAnnualInterestRate(),
                tenure,
                roundCurrency(emi),
                roundCurrency(totalPayableAmount),
                roundCurrency(totalInterest)
        );
    }

    @Override
    public EmiPaymentResponseDTO makeEmiPayment(EmiPaymentDTO dto) {
        if (dto == null) {
            throw new ValidationException("EMI payment request cannot be null");
        }

        LoanAccount loanAccount = loanAccountRepository.findById(dto.getLoanAccountId())
                .orElseThrow(() -> new LoanAccountNotFoundException("Loan account not found with ID: " + dto.getLoanAccountId()));

        if (loanAccount.getLoanStatus() == LoanStatus.CLOSED || loanAccount.getLoanStatus() == LoanStatus.REJECTED) {
            throw new ValidationException("EMI payment is not allowed for CLOSED or REJECTED loans");
        }

        PaymentType paymentType;
        try {
            paymentType = PaymentType.valueOf(dto.getPaymentType().trim().toUpperCase());
        } catch (Exception ex) {
            throw new ValidationException("Invalid paymentType: " + dto.getPaymentType());
        }

        EmiPayment payment = new EmiPayment();
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPenaltyPaid(dto.getPenaltyPaid());
        payment.setPaymentType(paymentType);
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setLoanAccount(loanAccount);

        EmiPayment saved = emiPaymentRepository.save(payment);
        log.info("Recorded EMI payment id={} accountId={} amount={}", saved.getEmiPaymentId(), loanAccount.getLoanAccountId(), saved.getAmountPaid());
        return toEmiPaymentResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerSummaryDTO getCustomerSummary(Long customerId) {
        return customerRepository.findCustomerSummaryById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardDTO getDashboard() {
        Long totalCustomers = customerRepository.count();
        Long totalLoans = loanAccountRepository.count();
        Double totalLoanAmount = valueOrZero(loanAccountRepository.findTotalLoanAmountDisbursed());
        Double totalPenalty = valueOrZero(emiPaymentRepository.findTotalPenaltyCollected());

        List<Object[]> topBranches = loanAccountRepository.findTotalLoanAmountPerBranch();
        String topBranch = topBranches.isEmpty() ? "N/A" : String.valueOf(topBranches.get(0)[0]);

        List<Object[]> highestLoanCustomers = loanAccountRepository.findHighestLoanCustomer();
        String highestLoanCustomer = highestLoanCustomers.isEmpty() ? "N/A" : String.valueOf(highestLoanCustomers.get(0)[0]);

        return new DashboardDTO(totalCustomers, totalLoans, totalLoanAmount, totalPenalty, topBranch, highestLoanCustomer);
    }

    private Customer resolveCustomerForAccount(Long requestedCustomerId, String authenticatedEmail, boolean privilegedUser) {
        if (!privilegedUser) {
            return customerRepository.findByEmail(authenticatedEmail)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found for authenticated user: " + authenticatedEmail));
        }

        if (requestedCustomerId != null) {
            return customerRepository.findById(requestedCustomerId)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + requestedCustomerId));
        }

        return customerRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found for authenticated user: " + authenticatedEmail));
    }

    private UserRole parseUserRole(String role) {
        try {
            return UserRole.valueOf(role.trim().toUpperCase());
        } catch (Exception ex) {
            throw new ValidationException("Invalid role: " + role);
        }
    }

    private LoanType parseLoanType(String loanType) {
        try {
            return LoanType.valueOf(loanType.trim().toUpperCase());
        } catch (Exception ex) {
            throw new ValidationException("Invalid loanType: " + loanType);
        }
    }

    private Double valueOrZero(Double value) {
        return value == null ? 0.0 : value;
    }

    private Double roundCurrency(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private CustomerResponseDTO toCustomerResponse(Customer customer) {
        return new CustomerResponseDTO(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getBranch(),
                customer.getRole() == null ? null : customer.getRole().name()
        );
    }

    private LoanProductResponseDTO toLoanProductResponse(LoanProduct entity) {
        return new LoanProductResponseDTO(
                entity.getLoanCode(),
                entity.getLoanName(),
                entity.getLoanType() == null ? null : entity.getLoanType().name(),
                entity.getInterestRate(),
                entity.getDailyPenaltyRate()
        );
    }

    private LoanAccountResponseDTO toLoanAccountResponse(LoanAccount entity) {
        return new LoanAccountResponseDTO(
                entity.getLoanAccountId(),
                entity.getLoanStartDate(),
                entity.getEmiDueDate(),
                entity.getLoanCloseDate(),
                entity.getLoanStatus() == null ? null : entity.getLoanStatus().name(),
                entity.getLoanAmount(),
                entity.getEmiAmount(),
                entity.getCustomer() == null ? null : entity.getCustomer().getCustomerId(),
                entity.getCustomer() == null ? null : entity.getCustomer().getCustomerName(),
                entity.getCustomer() == null ? null : entity.getCustomer().getEmail(),
                entity.getLoanProduct() == null ? null : entity.getLoanProduct().getLoanCode(),
                entity.getLoanProduct() == null || entity.getLoanProduct().getLoanType() == null ? null : entity.getLoanProduct().getLoanType().name()
        );
    }

    private EmiPaymentResponseDTO toEmiPaymentResponse(EmiPayment entity) {
        return new EmiPaymentResponseDTO(
                entity.getEmiPaymentId(),
                entity.getAmountPaid(),
                entity.getPenaltyPaid(),
                entity.getPaymentType() == null ? null : entity.getPaymentType().name(),
                entity.getPaymentDate(),
                entity.getLoanAccount() == null ? null : entity.getLoanAccount().getLoanAccountId()
        );
    }
}