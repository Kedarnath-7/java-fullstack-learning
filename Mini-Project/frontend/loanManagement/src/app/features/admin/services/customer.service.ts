import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { CustomerRequest, CustomerResponse, CustomerSummary } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private apiUrl = `${environment.apiUrl}/customers`;

  customers = signal<CustomerResponse[]>([]);
  selectedCustomer = signal<CustomerResponse | null>(null);
  customerSummary = signal<CustomerSummary | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  getAll() {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<CustomerResponse[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.customers.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load customers');
        this.loading.set(false);
      }
    });
  }

  getById(id: number) {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<CustomerResponse>(`${this.apiUrl}/${id}`).subscribe({
      next: (data) => {
        this.selectedCustomer.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load customer');
        this.loading.set(false);
      }
    });
  }

  getSummary(id: number) {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<CustomerSummary>(`${this.apiUrl}/${id}/summary`).subscribe({
      next: (data) => {
        this.customerSummary.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load summary');
        this.loading.set(false);
      }
    });
  }

  create(customer: CustomerRequest): Observable<CustomerResponse> {
    return this.http.post<CustomerResponse>(this.apiUrl, customer);
  }

  update(id: number, customer: CustomerRequest): Observable<CustomerResponse> {
    return this.http.put<CustomerResponse>(`${this.apiUrl}/${id}`, customer);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
