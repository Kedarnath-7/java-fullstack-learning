import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../../../environments/environment';
import { LoginRequest, LoginResponse, CustomerResponse } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = environment.apiUrl;

  currentUser = signal<CustomerResponse | null>(null);
  isLoggedIn = signal(false);
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    this.loadUserFromStorage();
  }

  private loadUserFromStorage() {
    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');
    if (user && token) {
      this.currentUser.set(JSON.parse(user));
      this.isLoggedIn.set(true);
    }
  }

  login(credentials: LoginRequest) {
    this.loading.set(true);
    this.error.set(null);

    this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.decodeAndStoreUser(response.token);
        this.isLoggedIn.set(true);
        this.loading.set(false);
        this.redirectByRole();
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.userMessage || 'Login failed');
      }
    });
  }

  private decodeAndStoreUser(token: string) {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const user: CustomerResponse = {
        customerId: payload.customerId || 0,
        customerName: payload.customerName || payload.sub,
        email: payload.sub,
        branch: payload.branch || '',
        role: payload.roles?.[0]?.replace('ROLE_', '') || 'USER'
      };
      localStorage.setItem('user', JSON.stringify(user));
      this.currentUser.set(user);
    } catch {
      this.error.set('Failed to decode token');
    }
  }

  private redirectByRole() {
    const role = this.currentUser()?.role;
    switch (role) {
      case 'ADMIN':
      case 'MANAGER':
        this.router.navigate(['/admin/dashboard']);
        break;
      case 'UNDERWRITER':
        this.router.navigate(['/underwriter/dashboard']);
        break;
      default:
        this.router.navigate(['/customer/dashboard']);
    }
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUser.set(null);
    this.isLoggedIn.set(false);
    this.router.navigate(['/auth/login']);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserRole(): string {
    return this.currentUser()?.role || '';
  }

  hasRole(...roles: string[]): boolean {
    return roles.includes(this.getUserRole());
  }
}
