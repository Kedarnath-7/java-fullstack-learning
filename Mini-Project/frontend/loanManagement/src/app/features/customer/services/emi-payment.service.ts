import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { EmiPaymentRequest, EmiPaymentResponse } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class EmiPaymentService {
  private apiUrl = `${environment.apiUrl}/emi-payments`;

  payments = signal<EmiPaymentResponse[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  create(payment: EmiPaymentRequest): Observable<EmiPaymentResponse> {
    return this.http.post<EmiPaymentResponse>(this.apiUrl, payment);
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
