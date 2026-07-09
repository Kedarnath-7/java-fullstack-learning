package com.northernarc.loanmanagement.config;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    @ConditionalOnProperty(name = "app.seed.enabled", havingValue = "true", matchIfMissing = true)
    CommandLineRunner seedInitialData(CustomerRepository customerRepository,
                                      LoanProductRepository loanProductRepository,
                                      LoanAccountRepository loanAccountRepository,
                                      EmiPaymentRepository emiPaymentRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            Customer admin = ensureCustomer(customerRepository, passwordEncoder,
                    "System Admin", "admin@northernarc.com", "Admin@123", "Hyderabad", UserRole.ADMIN);
            Customer manager = ensureCustomer(customerRepository, passwordEncoder,
                    "Branch Manager", "manager@northernarc.com", "Manager@123", "Hyderabad", UserRole.MANAGER);
            Customer underwriter = ensureCustomer(customerRepository, passwordEncoder,
                    "Loan Underwriter", "underwriter@northernarc.com", "Underwriter@123", "Hyderabad", UserRole.UNDERWRITER);
            Customer user = ensureCustomer(customerRepository, passwordEncoder,
                    "Rahul Sharma", "rahul.sharma@northernarc.com", "password123", "Hyderabad", UserRole.USER);

            ensureLoanProduct(loanProductRepository, "LP001", "Home Priority Loan", LoanType.HOME, 8.5, 2.8);
            ensureLoanProduct(loanProductRepository, "LP002", "Personal Flexi Loan", LoanType.PERSONAL, 11.5, 1.2);
            ensureLoanProduct(loanProductRepository, "LP003", "Education Assist Loan", LoanType.EDUCATION, 9.1, 0.7);

            LoanProduct home = loanProductRepository.findById("LP001").orElseThrow();
            LoanProduct personal = loanProductRepository.findById("LP002").orElseThrow();

            LoanAccount pending = ensureLoanApplication(loanAccountRepository, user, home,
                    LocalDate.now().minusDays(3), LocalDate.now().plusDays(27), 1500000.0, 14500.0, LoanStatus.PENDING);
            LoanAccount active = ensureLoanApplication(loanAccountRepository, user, personal,
                    LocalDate.now().minusDays(60), LocalDate.now().plusDays(5), 300000.0, 9200.0, LoanStatus.ACTIVE);

            ensurePayment(emiPaymentRepository, active, 9200.0, 250.0, PaymentType.UPI, LocalDate.now().minusDays(2));

            log.info("Initial data ready: admin={}, manager={}, underwriter={}, user={}, pendingApplicationId={}",
                    admin.getEmail(), manager.getEmail(), underwriter.getEmail(), user.getEmail(), pending.getLoanAccountId());
        };
    }

    private Customer ensureCustomer(CustomerRepository repository,
                                    PasswordEncoder encoder,
                                    String name,
                                    String email,
                                    String rawPassword,
                                    String branch,
                                    UserRole role) {
        return repository.findByEmail(email).orElseGet(() -> {
            Customer c = new Customer();
            c.setCustomerName(name);
            c.setEmail(email);
            c.setPassword(encoder.encode(rawPassword));
            c.setBranch(branch);
            c.setRole(role);
            return repository.save(c);
        });
    }

    private LoanProduct ensureLoanProduct(LoanProductRepository repository,
                                          String code,
                                          String name,
                                          LoanType loanType,
                                          Double interestRate,
                                          Double dailyPenaltyRate) {
        return repository.findById(code).orElseGet(() -> {
            LoanProduct p = new LoanProduct();
            p.setLoanCode(code);
            p.setLoanName(name);
            p.setLoanType(loanType);
            p.setInterestRate(interestRate);
            p.setDailyPenaltyRate(dailyPenaltyRate);
            return repository.save(p);
        });
    }

    private LoanAccount ensureLoanApplication(LoanAccountRepository repository,
                                              Customer customer,
                                              LoanProduct product,
                                              LocalDate startDate,
                                              LocalDate emiDueDate,
                                              Double amount,
                                              Double emiAmount,
                                              LoanStatus status) {
        return repository.findByCustomerEmail(customer.getEmail()).stream()
                .filter(a -> a.getLoanProduct() != null && codeOf(a.getLoanProduct()).equals(product.getLoanCode()))
                .findFirst()
                .orElseGet(() -> {
                    LoanAccount account = new LoanAccount();
                    account.setCustomer(customer);
                    account.setLoanProduct(product);
                    account.setLoanStartDate(startDate);
                    account.setEmiDueDate(emiDueDate);
                    account.setLoanAmount(amount);
                    account.setEmiAmount(emiAmount);
                    account.setLoanStatus(status);
                    return repository.save(account);
                });
    }

    private void ensurePayment(EmiPaymentRepository repository,
                               LoanAccount loanAccount,
                               Double amountPaid,
                               Double penaltyPaid,
                               PaymentType paymentType,
                               LocalDate paymentDate) {
        boolean exists = repository.findAll().stream()
                .anyMatch(p -> p.getLoanAccount() != null
                        && p.getLoanAccount().getLoanAccountId().equals(loanAccount.getLoanAccountId())
                        && p.getPaymentDate().equals(paymentDate));

        if (!exists) {
            EmiPayment payment = new EmiPayment();
            payment.setLoanAccount(loanAccount);
            payment.setAmountPaid(amountPaid);
            payment.setPenaltyPaid(penaltyPaid);
            payment.setPaymentType(paymentType);
            payment.setPaymentDate(paymentDate);
            repository.save(payment);
        }
    }

    private String codeOf(LoanProduct product) {
        return product == null ? "" : product.getLoanCode();
    }
}