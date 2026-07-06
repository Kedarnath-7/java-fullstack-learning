You are an experienced Java Spring Boot developer.

Carefully read the entire assessment document first before making any code changes.

Do NOT start implementing immediately.

First understand:
- Business domain
- Database schema
- Entity relationships
- All tasks
- Expected endpoints
- Repository requirements
- JPQL requirements
- Security requirements
- Dashboard analytics
- Constraints

The assessment is evaluated using automated test cases and viva questions.
Your goal is NOT just to make the application work.
Your goal is to implement EVERYTHING exactly according to the assessment specification while maximizing test case coverage.

====================================================
GENERAL IMPLEMENTATION RULES
====================================================

Implement only using the technologies mentioned in the assessment.
Do NOT introduce unnecessary advanced concepts.

Avoid:
- CQRS
- MapStruct
- Specifications API
- QueryDSL
- Reactive Programming
- WebFlux
- Custom Security Frameworks
- Design Patterns beyond basic Service-Repository architecture
- AOP unless explicitly asked
- Caching
- Async Programming
- Custom Serialization
- Native SQL unless explicitly required

Use simple and standard Spring Boot code.
Follow the existing project structure.

====================================================
PROJECT STRUCTURE
====================================================

Use only these layers:

Entity
Repository
DTO
Service
ServiceImpl
Controller
Exception
Security
Config
Utility
Filter

Keep business logic inside ServiceImpl.
Repositories should only contain database operations.
Controllers should remain thin.

====================================================
ENTITY
====================================================

Complete all entity mappings.
Use proper:
@OneToMany
@ManyToOne
@JoinColumn
mappedBy
CascadeType
FetchType

Initialize collections to avoid NullPointerException.
Use Lombok.

====================================================
VALIDATIONS
====================================================

Apply appropriate Jakarta Bean Validation.
Examples:
@NotNull
@NotBlank
@Email
@Positive
@PositiveOrZero
@Size
@Pattern
@Min
@Max

Apply validations in:
Entities (if expected)
AND
Request DTOs.
Validation messages should be meaningful.

====================================================
DTO MAPPING
====================================================

Never expose Entity directly from controllers.
Use DTOs.
Implement DTO mapping manually.
Do NOT use MapStruct.
Use DTO Projection where required by JPQL.

====================================================
CONTROLLERS
====================================================

Every controller method must return:
ResponseEntity<...>
Use proper HTTP status codes.
GET -> OK
POST -> CREATED
PUT -> OK
DELETE -> OK
Bad requests -> BAD_REQUEST
Not found -> NOT_FOUND

====================================================
EXCEPTION HANDLING
====================================================

Create:
GlobalExceptionHandler using @ControllerAdvice
Handle:
Validation exceptions
MethodArgumentNotValidException
Entity not found exceptions
Custom exceptions required by the assessment
Return proper ResponseEntity with meaningful error messages.

====================================================
REPOSITORY
====================================================

Implement:
Derived query methods exactly as required.
Implement JPQL queries exactly as required.
Prefer JPQL over native SQL.

====================================================
JPQL
====================================================
Write optimized JPQL.
Avoid unnecessary queries.
Use:
GROUP BY
HAVING
SUM
COUNT
COUNT(DISTINCT)
AVG
MAX
MIN
LEFT JOIN
ORDER BY

Subquery only if needed.
Use constructor projections where appropriate.

====================================================
PERFORMANCE
====================================================

Avoid N+1 query problem.
Avoid loading unnecessary entities.
Use aggregate queries.
Use joins whenever appropriate.
Use the minimum number of JPQL queries.
Assume the database contains a large amount of data.

====================================================
UPDATE QUERY
====================================================

Use
@Modifying
@Transactional
for update JPQL queries.

====================================================
PAGINATION
====================================================

Implement Pageable correctly.
Support sorting.
Default sorting should follow the assessment.

====================================================
SECURITY
====================================================

Implement completely:
UserDetailsService
AuthenticationManager
PasswordEncoder
JwtUtil
JwtFilter
SecurityConfig
POST /login
must be
permitAll()

Every remaining endpoint must require authentication.
JWT should be extracted from Authorization header.

====================================================
ROLE BASED AUTHORIZATION
====================================================

Use @PreAuthorize.
Implement exactly the permissions mentioned in the assessment.
No extra roles.

====================================================
SWAGGER
====================================================

Add OpenAPI/Swagger annotations where appropriate.
Document:
Controllers
Endpoints
Request DTOs
Response DTOs

====================================================
SERVICE LAYER
====================================================

Implement interfaces.
Business logic belongs only in ServiceImpl.

====================================================
TEST CASE FRIENDLINESS
====================================================

Write code that is easy for unit testing.
Avoid static methods except JwtUtil if needed.
Constructor injection preferred.
Keep methods deterministic.
Avoid hidden side effects.
Assume test cases will verify:
Mockito.when()

@Mock
@Spy
@MockBean
MockMvc.perform()
ResponseEntity
Validation
Exception handling
Repository calls
Security
JPQL

====================================================
DASHBOARD
====================================================

Implement the dashboard exactly as described.
Use the minimum possible optimized JPQL queries.
Avoid sequential entity loading.
Avoid N+1 query issue.

====================================================
FINAL CHECKLIST
====================================================

Before considering the assessment complete, verify ALL of the following:

✓ All tasks from the assessment document are implemented.
✓ All entities are correctly mapped.
✓ Bean Validation is complete.
✓ Controllers return ResponseEntity.
✓ DTO mapping is complete.
✓ Service and ServiceImpl are complete.
✓ Repository methods are complete.
✓ Derived queries are complete.
✓ JPQL queries are complete.
✓ Update JPQL query implemented.
✓ Pagination implemented.
✓ Sorting implemented.
✓ JWT Authentication implemented.
✓ UserDetailsService implemented.
✓ JwtUtil implemented.
✓ JwtFilter implemented.
✓ SecurityConfig implemented.
✓ AuthenticationManager configured.
✓ PasswordEncoder configured.
✓ Role-based authorization implemented.
✓ Swagger/OpenAPI documentation added.
✓ Global Exception Handler implemented.
✓ Validation exceptions handled.
✓ Custom exceptions handled.
✓ Dashboard implemented.
✓ JPQL optimized.
✓ No unnecessary advanced concepts introduced.
✓ Code follows standard Spring Boot best practices.
✓ Implementation matches the assessment document exactly.

Now read the assessment document carefully from start to finish, then inspect the existing project files, identify what is already implemented and what is missing, and complete only the missing or incorrect parts while preserving the existing project structure and coding style.
Do not assume anything. If a task explicitly specifies a field name, endpoint, DTO, JPQL logic, sorting order, validation, or role permission, implement it exactly as written in the assessment document instead of using your own naming or design