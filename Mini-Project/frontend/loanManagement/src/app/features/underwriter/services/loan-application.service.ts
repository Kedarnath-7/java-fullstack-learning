import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { LoanAccountResponse, ApiMessage } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class LoanApplicationService {
  private apiUrl = `${environment.apiUrl}/loan-applications`;

  applications = signal<LoanAccountResponse[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  getAll() {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<LoanAccountResponse[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.applications.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load applications');
        this.loading.set(false);
      }
    });
  }

  approve(id: number): Observable<ApiMessage> {
    return this.http.put<ApiMessage>(`${this.apiUrl}/${id}/approve`, null);
  }

  reject(id: number): Observable<ApiMessage> {
    return this.http.put<ApiMessage>(`${this.apiUrl}/${id}/reject`, null);
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
