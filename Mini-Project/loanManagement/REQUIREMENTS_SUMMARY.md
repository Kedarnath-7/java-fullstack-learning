# Loan Management API - Complete Requirements Summary

## Overview
- **Type**: Secure REST API for Loan Management
- **Duration**: 120 minutes
- **Max Marks**: 400 (200 Functional + 200 Hidden Test Cases)
- **Stack**: Spring Boot + Spring Data JPA + JPQL + JWT + PostgreSQL

## Business Scenario
National banking organization managing customer loans, EMI repayments, overdue penalties with configurable daily rates, customer records, and analytical reports across multiple branches.

---

## Database Schema & Entity Models

### 1. Customer Entity
- **Primary Key**: `customerId` (Long)
- **Fields**:
  - `customerName` (String)
  - `email` (String)
  - `password` (String)
  - `branch` (String)
- **Relationships**:
  - One-to-Many with LoanAccount

### 2. LoanProduct Entity
- **Primary Key**: `loanCode` (String)
- **Fields**:
  - `loanName` (String)
  - `loanType` (Enum: PERSONAL, HOME, VEHICLE, EDUCATION, BUSINESS)
  - `interestRate` (Double)
  - `dailyPenaltyRate` (Double)
- **Relationships**:
  - One-to-Many with LoanAccount

### 3. LoanAccount Entity
- **Primary Key**: `loanAccountId` (Long)
- **Fields**:
  - `loanStartDate` (LocalDate)
  - `emiDueDate` (LocalDate)
  - `loanCloseDate` (LocalDate, Nullable)
  - `status` (Enum: ACTIVE, CLOSED, OVERDUE)
  - `loanAmount` (Double)
  - `emiAmount` (Double)
- **Relationships**:
  - Many-to-One with Customer
  - Many-to-One with LoanProduct
  - One-to-Many with EmiPayment

### 4. EmiPayment Entity
- **Primary Key**: `paymentId` (Long)
- **Fields**:
  - `amountPaid` (Double)
  - `penaltyPaid` (Double)
  - `paymentType` (Enum: CASH, CARD, ONLINE, UPI)
  - `paymentDate` (LocalDate)
- **Relationships**:
  - Many-to-One with LoanAccount

### Entity Relationships Diagram
```
Customer (1) ---- (*) LoanAccount
LoanAccount (*) ---- (1) LoanProduct
LoanAccount (1) ---- (*) EmiPayment
```

---

## Tasks Breakdown

### Task 1: Complete Entity Mapping (10 Marks)
**Requirements**:
- Configure `@OneToMany`, `@ManyToOne`
- Set appropriate `CascadeType`
- Configure `mappedBy` correctly
- Set appropriate `FetchType`
- Initialize collections safely

### Task 2: Bean Validation (10 Marks)
**Apply Validations**:
- `@NotBlank` - for required string fields
- `@Email` - for email fields
- `@Positive` - for amounts that must be positive
- `@PositiveOrZero` - for values >= 0
- `@Size` - for string length constraints
- `@NotNull` - for required fields
**Key Fields**: loanAmount, emiAmount, dailyPenaltyRate

### Task 3: Spring Data JPA Derived Queries (15 Marks)
**Required Methods**:
1. `List<LoanProduct> findByLoanType(String loanType)`
2. `List<Customer> findByBranch(String branch)`
3. `List<EmiPayment> findByPaymentType(String paymentType)`
4. `List<LoanProduct> findByDailyPenaltyRateGreaterThan(double rate)`

### Task 4: JPQL Queries (50 Marks)
**Required Queries**:

1. **Find Premium Borrowers**
   - Find customers with more than N loan accounts
   - Use COUNT and GROUP BY

2. **Find Total Penalty Collected Per Branch**
   - Use JOIN + GROUP BY + SUM()
   - Aggregate penalty collection by branch

3. **Find Customers Using Multiple Loan Types**
   - Use COUNT(DISTINCT), GROUP BY and HAVING
   - Find customers with diverse loan portfolio

4. **Find Latest EMI Payment**
   - Use ORDER BY with date/time sorting
   - Return most recent payment

5. **Find Loan Products With No Overdue History**
   - Use LEFT JOIN or NOT EXISTS
   - Products never associated with OVERDUE status

### Task 5: JPQL Update Query (10 Marks)
**Method**: `increaseDailyPenaltyRates()`
- Update penalty rates for selected loan categories
- Use `@Modifying` annotation
- Use `@Transactional` annotation
- Bulk update operation

### Task 6: Pagination & Sorting (10 Marks)
**Endpoint**: `GET /loan-products`
- Implement Pageable support
- Default sorting: `dailyPenaltyRate DESC`
- Support page number and size parameters

### Task 7: DTO Projection Mapping (10 Marks)
**CustomerSummaryDTO** fields:
- `customerName` (String)
- `branch` (String)
- `numberOfLoans` (Integer)
- `totalLoanAmount` (Double)
- `totalPenaltyPaid` (Double)

### Task 8: JWT Authentication (25 Marks)
**Components to Implement**:
- `UserDetailsService` - Load user details
- `AuthenticationManager` - Authentication configuration
- `PasswordEncoder` - BCrypt password encoding
- `JwtFilter` - Token validation filter
- `JwtUtil` - Token generation and validation utilities
- `SecurityConfig` - Security configuration

**Security Rules**:
- `POST /login` - permitAll() (public)
- All other APIs - require authentication

### Task 9: Role-Based Authorization (10 Marks)
**Roles & Permissions**:

**ADMIN**:
- Delete Loan Products
- Delete Loan Accounts

**MANAGER**:
- Update penalty rates
- Approve loans
- Close loans

**USER**:
- View loan products
- View own loan details
- Make EMI payments

### Task 10: Global Exception Handling (10 Marks)
**Implement @ControllerAdvice for**:
- `CustomerNotFoundException`
- `LoanProductNotFoundException`
- `LoanAccountNotFoundException`
- `ValidationException`

Return proper HTTP status codes and error messages

---

## Final Challenge: Dashboard API (40 Marks)

### Endpoint: `GET /dashboard`

**Response Structure**:
```json
{
  "totalCustomers": 8200,
  "totalLoans": 5200,
  "totalLoanAmountDisbursed": 845000000.00,
  "totalPenaltyCollected": 1275000.75,
  "topBranch": "Hyderabad",
  "highestLoanCustomer": "Rahul Sharma"
}
```

### **Critical Constraints**:
1. ✅ Use **minimum number of optimized JPQL queries**
2. ✅ **Avoid sequential entity loading**
3. ✅ **Prevent N+1 query problem**
4. ✅ Use **aggregate functions** wherever possible
5. ✅ Ensure **efficient execution for large datasets**

### Dashboard Fields Explained:
- **totalCustomers**: Total count of customers
- **totalLoans**: Total count of loan accounts
- **totalLoanAmountDisbursed**: SUM of all loan amounts
- **totalPenaltyCollected**: SUM of all penalty payments
- **topBranch**: Branch with highest loan amount disbursed
- **highestLoanCustomer**: Customer with highest total loan amount

---

## API Endpoints Summary

### Authentication
- `POST /login` - Login and get JWT token (public)

### Loan Products
- `GET /loan-products` - List with pagination & sorting
- `DELETE /loan-products/{loanCode}` - Delete (ADMIN only)

### Loan Accounts
- `GET /loan-accounts` - View loan accounts
- `DELETE /loan-accounts/{id}` - Delete (ADMIN only)
- `PUT /loan-accounts/{id}/close` - Close loan (MANAGER only)

### EMI Payments
- `POST /emi-payments` - Make payment (USER)

### Penalty Management
- `PUT /penalty-rates/increase` - Bulk update (MANAGER only)

### Dashboard
- `GET /dashboard` - Analytics dashboard

### API Documentation
- Swagger/OpenAPI available at `/swagger-ui.html` or `/v3/api-docs`

---

## Repository Methods Required

### CustomerRepository
- Derived: `findByBranch(String branch)`
- JPQL: Premium borrowers query
- JPQL: Customers using multiple loan types
- Custom: Customer summary projection

### LoanProductRepository
- Derived: `findByLoanType(String loanType)`
- Derived: `findByDailyPenaltyRateGreaterThan(double rate)`
- JPQL: Update penalty rates (@Modifying)
- JPQL: Products with no overdue history
- Pageable support

### LoanAccountRepository
- JPQL: Total penalty per branch
- JPQL: Premium borrowers support
- Custom: Dashboard aggregations

### EmiPaymentRepository
- Derived: `findByPaymentType(String paymentType)`
- JPQL: Latest EMI payment
- JPQL: Total penalty calculations

---

## Validation Rules

### Customer
- `customerName`: @NotBlank, @Size(min=2, max=100)
- `email`: @NotBlank, @Email
- `password`: @NotBlank, @Size(min=8)
- `branch`: @NotBlank

### LoanProduct
- `loanCode`: @NotBlank
- `loanName`: @NotBlank
- `loanType`: @NotNull
- `interestRate`: @Positive
- `dailyPenaltyRate`: @PositiveOrZero

### LoanAccount
- `loanStartDate`: @NotNull
- `emiDueDate`: @NotNull
- `status`: @NotNull
- `loanAmount`: @Positive
- `emiAmount`: @Positive

### EmiPayment
- `amountPaid`: @Positive
- `penaltyPaid`: @PositiveOrZero
- `paymentType`: @NotNull
- `paymentDate`: @NotNull

---

## Security Configuration

### JWT Setup
- Token expiration time
- Secret key configuration
- Token prefix: "Bearer "
- Header: "Authorization"

### Password Encoding
- Use BCryptPasswordEncoder
- Strength: 10-12 rounds

### Filter Chain
1. JwtFilter (before UsernamePasswordAuthenticationFilter)
2. Authentication validation
3. SecurityContext population
4. Role-based access control
5. Exception handling for unauthorized access
6. Duplicate email or invalid credentials handling
---

### Use Cases to be Implemented
1. **Customer Management**: Create, Read, Update, Delete (CRUD) operations for customers.
2. **Loan Product Management**: CRUD operations for loan products, including filtering by type and penalty rates.
3. **Loan Account Management**: CRUD operations for loan accounts, including closing loans and viewing loan details.
4. **EMI Payment Processing**: Record EMI payments, including penalty calculations and payment type handling.
5. **Penalty Rate Management**: Update daily penalty rates for specific loan products, with role-based access control.
6. **Dashboard Analytics**: Provide aggregated data for total customers, loans, loan amounts, penalties, top branches, and highest loan customers.
7. **Authentication & Authorization**: Implement JWT-based authentication and role-based authorization for secure access to endpoints.
8. **Exception Handling**: Implement global exception handling for validation errors, resource not found, and unauthorized access.
9. **API Documentation**: Provide Swagger/OpenAPI documentation for all endpoints, including request/response models and authentication requirements.
10. **Testing & Validation**: Ensure all endpoints are covered by unit and integration tests, with proper validation and error handling.
in summary - customer can apply for loan & can see the details of the loan, underwriter find all loan application & can approve/reject loanapplicantion, manager can see the dashboard & can update the penalty rates, admin can delete loan products & loan accounts.

## Technologies & Dependencies

- **Java**: 17+
- **Spring Boot**: 3.x
- **Spring Data JPA**: For database operations
- **PostgreSQL**: Database
- **Spring Security**: Authentication & Authorization
- **JWT**: Token-based authentication
- **Jakarta Bean Validation**: Input validation
- **Lombok**: Reduce boilerplate code
- **Swagger/OpenAPI**: API documentation
---

## Project Structure

```
entity/
  - Customer.java
  - LoanProduct.java
  - LoanAccount.java
  - EmiPayment.java

repository/
  - CustomerRepository.java
  - LoanProductRepository.java
  - LoanAccountRepository.java
  - EmiPaymentRepository.java

service/
  - LoanService.java

serviceimpl/
  - LoanServiceImpl.java

controller/
  - LoanController.java

security/
  - JwtFilter.java
  - JwtUtil.java
  - SecurityConfig.java

dto/
  - CustomerSummaryDTO.java
  - DashboardDTO.java
  - (other DTOs as needed)

exception/
  - CustomerNotFoundException.java
  - LoanProductNotFoundException.java
  - LoanAccountNotFoundException.java
  - ValidationException.java
  - GlobalExceptionHandler.java
```

---

## Performance Optimization Tips

1. **Use @Query with JOIN FETCH** to avoid N+1 problems
2. **Use aggregate functions** (COUNT, SUM, AVG, MAX, MIN)
3. **Batch operations** where possible
4. **Pagination** for large result sets
5. **Proper indexing** on frequently queried fields
6. **DTO projections** instead of full entities when possible
7. **@Transactional(readOnly=true)** for read operations

---

## Testing Checklist

- [ ] All entity mappings correct
- [ ] Bean validation working
- [ ] Derived queries returning correct results
- [ ] JPQL queries optimized and correct
- [ ] Update query works with @Modifying
- [ ] Pagination and sorting working
- [ ] DTO projection mapping correct
- [ ] JWT authentication working
- [ ] Role-based authorization enforced
- [ ] Exception handling returning proper responses
- [ ] Dashboard API optimized and accurate
- [ ] No N+1 query problems
- [ ] All test cases passing

---

## Key Success Criteria

✅ **Functional Completeness**: All endpoints working
✅ **Security**: Proper JWT authentication and authorization
✅ **Performance**: Optimized queries, no N+1 problems
✅ **Validation**: All inputs properly validated
✅ **Exception Handling**: Graceful error responses
✅ **Code Quality**: Clean, maintainable code
✅ **Test Coverage**: All test cases passing (400/400 marks)
