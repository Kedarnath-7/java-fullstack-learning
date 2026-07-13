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

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'products', component: ProductList },
  { path: 'products/:id', component: ProductDetail },
  { path: 'cart', component: Cart },
  { path: 'orders', component: OrderList },
  { path: 'orders/:id', component: OrderDetail },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'profile', component: Profile },
  { path: '**', redirectTo: '' }
];
