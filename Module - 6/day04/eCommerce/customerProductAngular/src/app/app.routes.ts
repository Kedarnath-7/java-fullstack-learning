import { Routes } from '@angular/router';
import { Home } from './features/home/components/home/home';
import { ProductList } from './features/product/components/product-list/product-list';
import { ProductDetail } from './features/product/components/product-detail/product-detail';
import { Cart } from './features/order/components/cart/cart';
import { OrderList } from './features/order/components/order-list/order-list';
import { OrderDetail } from './features/order/components/order-detail/order-detail';
import { Login } from './features/customer/components/login/login';
import { Register } from './features/customer/components/register/register';
import { Profile } from './features/customer/components/profile/profile';
import { AdminDashboard } from './features/admin/components/admin-dashboard/admin-dashboard';
import { AdminProducts } from './features/admin/components/admin-products/admin-products';
import { AdminProductForm } from './features/admin/components/admin-product-form/admin-product-form';
import { AdminOrders } from './features/admin/components/admin-orders/admin-orders';
import { AdminCustomers } from './features/admin/components/admin-customers/admin-customers';
import { authGuard } from './shared/guards/auth.guard';
import { adminGuard } from './shared/guards/admin.guard';

export const routes: Routes = [
  // Public routes
  { path: '', component: Home },
  { path: 'products', component: ProductList },
  { path: 'products/:id', component: ProductDetail },
  { path: 'login', component: Login },
  { path: 'register', component: Register },

  // Authenticated user routes
  { path: 'cart', component: Cart, canActivate: [authGuard] },
  { path: 'orders', component: OrderList, canActivate: [authGuard] },
  { path: 'orders/:id', component: OrderDetail, canActivate: [authGuard] },
  { path: 'profile', component: Profile, canActivate: [authGuard] },

  // Admin routes
  { path: 'admin', component: AdminDashboard, canActivate: [adminGuard] },
  { path: 'admin/products', component: AdminProducts, canActivate: [adminGuard] },
  { path: 'admin/products/add', component: AdminProductForm, canActivate: [adminGuard] },
  { path: 'admin/products/edit/:id', component: AdminProductForm, canActivate: [adminGuard] },
  { path: 'admin/orders', component: AdminOrders, canActivate: [adminGuard] },
  { path: 'admin/customers', component: AdminCustomers, canActivate: [adminGuard] },

  { path: '**', redirectTo: '' }
];
