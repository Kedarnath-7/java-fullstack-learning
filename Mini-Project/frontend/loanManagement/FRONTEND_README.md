# NorthernArc Loan Management - Frontend

Angular 21 frontend application for the Loan Management System.

## How to Run

```bash
cd frontend/loanManagement
npm install
ng serve
```

The app will be available at `http://localhost:4200`.

## Backend URL Configuration

The backend API URL is configured in:
- `src/environments/environment.ts` (development)
- `src/environments/environment.prod.ts` (production)

Default: `http://localhost:8080`

Ensure the Spring Boot backend is running before using the frontend.

## Project Structure

```
src/app/
├── core/
│   ├── models/          → TypeScript interfaces matching backend DTOs
│   └── interceptors/    → HTTP interceptors (auth, logging, error)
├── features/
│   ├── shared/
│   │   ├── components/  → Navbar, Footer, Loading, EmptyState
│   │   └── services/    → DashboardService
│   ├── auth/
│   │   ├── components/  → Login, Register
│   │   └── services/    → AuthService (JWT login, token management)
│   ├── public/
│   │   └── components/  → Landing, LoanProducts (public catalog)
│   ├── customer/
│   │   ├── components/  → Dashboard, ApplyLoan, MyLoans, LoanDetail, MakePayment
│   │   └── services/    → LoanAccountService, EmiPaymentService
│   ├── underwriter/
│   │   ├── components/  → UnderwriterDashboard, ApplicationReview
│   │   └── services/    → LoanApplicationService
│   └── admin/
│       ├── components/  → AdminDashboard, CustomerList/Form, LoanProductList/Form, AllLoans
│       └── services/    → CustomerService, LoanProductService, PenaltyRateService
```

## Screens & Backend Endpoint Mapping

| Screen | Route | Backend Endpoint(s) |
|--------|-------|-------------------|
| Login | `/auth/login` | `POST /login` |
| Register | `/auth/register` | `POST /customers` |
| Landing | `/` | — (static) |
| Loan Products (public) | `/loan-products` | `GET /loan-products` |
| Customer Dashboard | `/customer/dashboard` | `GET /loan-accounts`, `GET /customers/{id}/summary` |
| Apply for Loan | `/customer/apply-loan` | `POST /loan-accounts`, `GET /loan-products` |
| My Loans | `/customer/my-loans` | `GET /loan-accounts` |
| Loan Detail | `/customer/loan-detail/:id` | `GET /loan-accounts/{id}` |
| Make Payment | `/customer/make-payment` | `POST /emi-payments`, `GET /loan-accounts` |
| Underwriter Dashboard | `/underwriter/dashboard` | `GET /loan-applications` |
| Application Review | `/underwriter/review/:id` | `PUT /loan-applications/{id}/approve`, `PUT /loan-applications/{id}/reject` |
| Admin Dashboard | `/admin/dashboard` | `GET /dashboard` |
| Customer List | `/admin/customers` | `GET /customers` |
| Customer Form | `/admin/customers/new`, `/admin/customers/edit/:id` | `POST /customers`, `PUT /customers/{id}` |
| Loan Product List | `/admin/loan-products` | `GET /loan-products` |
| Loan Product Form | `/admin/loan-products/new`, `/admin/loan-products/edit/:code` | `POST /loan-products`, `PUT /loan-products/{loanCode}` |
| All Loans | `/admin/all-loans` | `GET /loan-accounts`, `DELETE /loan-accounts/{id}`, `PUT /loan-accounts/{id}/close` |

## Key Features

- **JWT Authentication** — Token stored in localStorage, injected via HTTP interceptor
- **Role-based Navigation** — Navbar adapts based on user role (USER, ADMIN, MANAGER, UNDERWRITER)
- **HTTP Logging** — All API requests/responses logged to browser console with timing
- **Global Error Handling** — User-friendly messages for 4xx/5xx errors, connection failures
- **Angular Signals** — Used for reactive local state (loading, error, success)
- **Reusable Components** — Loading spinner, Empty state placeholder
- **Bootstrap 5** — Responsive UI with cards, tables, badges, forms

## Demo Credentials

| Role | Email | Password |
|------|-------|----------|
| ADMIN | admin@northernarc.com | Admin@123 |
| MANAGER | manager@northernarc.com | Manager@123 |
| UNDERWRITER | underwriter@northernarc.com | Underwriter@123 |
| USER | rahul.sharma@northernarc.com | password123 |
