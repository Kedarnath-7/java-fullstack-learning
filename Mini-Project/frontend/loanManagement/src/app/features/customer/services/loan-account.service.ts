import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { LoanAccountRequest, LoanAccountResponse, ApiMessage } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class LoanAccountService {
  private apiUrl = `${environment.apiUrl}/loan-accounts`;

  loanAccounts = signal<LoanAccountResponse[]>([]);
  selectedLoan = signal<LoanAccountResponse | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  getAll() {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<LoanAccountResponse[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.loanAccounts.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load loan accounts');
        this.loading.set(false);
      }
    });
  }

  getById(id: number) {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<LoanAccountResponse>(`${this.apiUrl}/${id}`).subscribe({
      next: (data) => {
        this.selectedLoan.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load loan details');
        this.loading.set(false);
      }
    });
  }

  create(loan: LoanAccountRequest): Observable<LoanAccountResponse> {
    return this.http.post<LoanAccountResponse>(this.apiUrl, loan);
  }

  update(id: number, loan: LoanAccountRequest): Observable<LoanAccountResponse> {
    return this.http.put<LoanAccountResponse>(`${this.apiUrl}/${id}`, loan);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  close(id: number): Observable<ApiMessage> {
    return this.http.put<ApiMessage>(`${this.apiUrl}/${id}/close`, null);
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
