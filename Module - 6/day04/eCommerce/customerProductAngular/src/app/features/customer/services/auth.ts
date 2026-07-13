import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthLoginRequest, AuthRegisterRequest, AuthResponse } from '../../../shared/models/auth.model';

const BASE_URL = 'http://localhost:8080/api/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  register(request: AuthRegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${BASE_URL}/register`, request).pipe(
      tap(response => this.storeAuth(response))
    );
  }

  login(request: AuthLoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${BASE_URL}/login`, request).pipe(
      tap(response => this.storeAuth(response))
    );
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('customer_id');
    localStorage.removeItem('customer_email');
    localStorage.removeItem('customer_role');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('auth_token');
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  getCustomerId(): number | null {
    const id = localStorage.getItem('customer_id');
    return id ? Number(id) : null;
  }

  getCustomerEmail(): string | null {
    return localStorage.getItem('customer_email');
  }

  getCustomerRole(): string | null {
    return localStorage.getItem('customer_role');
  }

  private storeAuth(response: AuthResponse): void {
    localStorage.setItem('auth_token', response.token);
    localStorage.setItem('customer_id', String(response.customerId));
    localStorage.setItem('customer_email', response.email);
    localStorage.setItem('customer_role', response.role);
  }
}
