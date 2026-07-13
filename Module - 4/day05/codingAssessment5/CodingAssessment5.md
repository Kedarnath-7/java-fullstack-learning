**Spring Boot TDD Challenge -- Secure Banking API**

**Duration**

**2 Hours**

**(Spring Boot + JUnit 5 + Mockito + MockMvc + TDD)**

**Objective**

**You have joined the backend engineering team at ABC Digital Bank.**

**The product owner has finalized the functional requirements for a secure banking application. The development team has not yet started implementation.**

**Your responsibility is to design and implement the automated test suite first following Test-Driven Development (TDD) principles.**

**The production code does not exist yet.**

**Your test suite should clearly define the expected behavior of the application and act as the executable specification that developers will later implement.**

**Technology Stack**

- **Java 21**

- **Spring Boot 3.x**

- **JUnit 5**

- **Mockito**

- **MockMvc**

- **Spring Boot Test**

- **Spring Data JPA**

- **H2 Database**

- **Jackson**

- **Spring Security**

- **JWT Authentication**

**Challenge Overview**

**The development team will implement the application later.**

**Your responsibility is to write comprehensive automated tests that specify the expected behavior of:**

- **REST Controllers**

- **Service Layer**

- **Repository Layer**

- **Security**

- **Validation**

- **Exception Handling**

- **Database Operations**

**Your tests should serve as the functional specification for the application.**

**Existing Project Structure**

**src**

**├── main**

**│**

**│ (Contains only empty packages and interfaces)**

**│**

**│ controller**

**│ service**

**│ repository**

**│ entity**

**│ dto**

**│ security**

**│ exception**

**│**

**└── test**

**\<\-- You will implement this package**

**Domain Model**

**Customer**

**(** **id\
name\
email\
phone)**

**1 \-\-\-\-\-\-\-- \***

**Account**

**(id\
accountNumber\
accountType\
balance)**

**1 \-\-\-\-\-\-\-- \***

**Transaction**

**(id\
transactionType\
amount\
transactionDate\
description)**

**REST APIs to be Developed**

**Authentication**

| **Method** | **Endpoint**           |
|------------|------------------------|
| **POST**   | **/api/auth/register** |
| **POST**   | **/api/auth/login**    |

**Customer APIs**

| **Method** | **Endpoint**            |
|------------|-------------------------|
| **POST**   | **/api/customers**      |
| **GET**    | **/api/customers**      |
| **GET**    | **/api/customers/{id}** |
| **PUT**    | **/api/customers/{id}** |
| **DELETE** | **/api/customers/{id}** |

**Account APIs**

| **Method** | **Endpoint**           |
|------------|------------------------|
| **POST**   | **/api/accounts**      |
| **GET**    | **/api/accounts**      |
| **GET**    | **/api/accounts/{id}** |
| **PUT**    | **/api/accounts/{id}** |
| **DELETE** | **/api/accounts/{id}** |

**Banking Operations**

| **Method** | **Endpoint**               |
|------------|----------------------------|
| **POST**   | **/api/accounts/deposit**  |
| **POST**   | **/api/accounts/withdraw** |
| **POST**   | **/api/accounts/transfer** |

**Transaction APIs**

| **Method** | **Endpoint**                        |
|------------|-------------------------------------|
| **GET**    | **/api/transactions**               |
| **GET**    | **/api/transactions/{id}**          |
| **GET**    | **/api/accounts/{id}/transactions** |

**Business Rules**

**Your tests should verify the following requirements.**

**Customer**

- **Customer email must be unique.**

- **Customer name is mandatory.**

- **Email must be valid.**

- **Phone number must contain exactly 10 digits.**

- **Password must be encrypted.**

- **Duplicate customer registration should return 409 Conflict.**

**Account**

- **Opening balance cannot be negative.**

- **Account number must be unique.**

- **Customer must exist before creating an account.**

- **Account type can only be SAVINGS or CURRENT.**

**Deposit**

- **Deposit amount must be greater than zero.**

- **Account must exist.**

- **Account balance should increase correctly.**

- **A transaction record must be created.**

**Withdrawal**

- **Withdrawal amount must be greater than zero.**

- **Withdrawal cannot exceed available balance.**

- **A transaction record must be created.**

- **Insufficient balance should return 400 Bad Request.**

**Transfer**

- **Source account must exist.**

- **Destination account must exist.**

- **Source and destination cannot be the same account.**

- **Transfer amount must be positive.**

- **Transfer must be atomic.**

- **Two transaction records must be created.**

**Security**

- **/login and /register are public.**

- **All other endpoints require JWT authentication.**

- **Invalid JWT returns 401 Unauthorized.**

- **Expired JWT returns 401 Unauthorized.**

- **Missing JWT returns 401 Unauthorized.**

**Exception Handling**

**Expected HTTP status codes:**

| **Exception**        | **Status** |
|----------------------|------------|
| **CustomerNotFound** | **404**    |
| **AccountNotFound**  | **404**    |
| **DuplicateEmail**   | **409**    |
| **ValidationError**  | **400**    |
| **Unauthorized**     | **401**    |
| **Forbidden**        | **403**    |

**Your Task**

**Write automated tests before any implementation exists.**

**Your tests should define the expected behavior of:**

- **Service classes**

- **Controllers**

- **Repository layer**

- **Security layer**

- **Exception handling**

- **Validation**

- **Integration flow**

**Expected Test Classes**

**CustomerServiceTest**

**AccountServiceTest**

**TransactionServiceTest**

**CustomerControllerTest**

**AccountControllerTest**

**TransactionControllerTest**

**CustomerRepositoryTest**

**AccountRepositoryTest**

**SecurityTest**

**GlobalExceptionHandlerTest**

**CustomerIntegrationTest**

**TransferIntegrationTest**

**Expectations**

**A high-quality submission should:**

- **Follow the Arrange--Act--Assert (AAA) pattern.**

- **Use meaningful and descriptive test method names.**

- **Verify both successful and failure scenarios.**

- **Cover positive, negative, and edge cases.**

- **Avoid duplicated test code.**

- **Produce readable, maintainable tests that can guide implementation.**
