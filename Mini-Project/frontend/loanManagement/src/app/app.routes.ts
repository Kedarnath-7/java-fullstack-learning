import { Routes } from '@angular/router';
import { Landing } from './features/public/components/landing/landing';
import { LoanProducts } from './features/public/components/loan-products/loan-products';
import { Login } from './features/auth/components/login/login';
import { Register } from './features/auth/components/register/register';
import { CustomerDashboard } from './features/customer/components/customer-dashboard/customer-dashboard';
import { ApplyLoan } from './features/customer/components/apply-loan/apply-loan';
import { MyLoans } from './features/customer/components/my-loans/my-loans';
import { LoanDetail } from './features/customer/components/loan-detail/loan-detail';
import { MakePayment } from './features/customer/components/make-payment/make-payment';
import { UnderwriterDashboard } from './features/underwriter/components/underwriter-dashboard/underwriter-dashboard';
import { ApplicationReview } from './features/underwriter/components/application-review/application-review';
import { AdminDashboard } from './features/admin/components/admin-dashboard/admin-dashboard';
import { CustomerList } from './features/admin/components/customer-list/customer-list';
import { CustomerForm } from './features/admin/components/customer-form/customer-form';
import { LoanProductList } from './features/admin/components/loan-product-list/loan-product-list';
import { LoanProductForm } from './features/admin/components/loan-product-form/loan-product-form';
import { AllLoans } from './features/admin/components/all-loans/all-loans';
import { Unauthorized } from './features/shared/components/unauthorized/unauthorized';
import { authGuard, roleGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  // Public
  { path: '', component: Landing },
  { path: 'loan-products', component: LoanProducts },
  { path: 'unauthorized', component: Unauthorized },

  // Auth
  { path: 'auth/login', component: Login },
  { path: 'auth/register', component: Register },

  // Customer (USER, ADMIN, MANAGER can access)
  { path: 'customer/dashboard', component: CustomerDashboard, canActivate: [roleGuard('USER', 'ADMIN', 'MANAGER')] },
  { path: 'customer/apply-loan', component: ApplyLoan, canActivate: [roleGuard('USER', 'ADMIN', 'MANAGER')] },
  { path: 'customer/my-loans', component: MyLoans, canActivate: [roleGuard('USER', 'ADMIN', 'MANAGER')] },
  { path: 'customer/loan-detail/:id', component: LoanDetail, canActivate: [authGuard] },
  { path: 'customer/make-payment', component: MakePayment, canActivate: [roleGuard('USER')] },

  // Underwriter
  { path: 'underwriter/dashboard', component: UnderwriterDashboard, canActivate: [roleGuard('UNDERWRITER')] },
  { path: 'underwriter/review/:id', component: ApplicationReview, canActivate: [roleGuard('UNDERWRITER')] },

  // Admin (ADMIN, MANAGER)
  { path: 'admin/dashboard', component: AdminDashboard, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/customers', component: CustomerList, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/customers/new', component: CustomerForm, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/customers/edit/:id', component: CustomerForm, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/loan-products', component: LoanProductList, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/loan-products/new', component: LoanProductForm, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/loan-products/edit/:code', component: LoanProductForm, canActivate: [roleGuard('ADMIN', 'MANAGER')] },
  { path: 'admin/all-loans', component: AllLoans, canActivate: [roleGuard('ADMIN', 'MANAGER')] },

  // Fallback
  { path: '**', redirectTo: '' }
];
