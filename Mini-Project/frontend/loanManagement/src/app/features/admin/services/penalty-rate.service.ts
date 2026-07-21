import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { ApiMessage } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class PenaltyRateService {
  private apiUrl = `${environment.apiUrl}/penalty-rates`;

  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  increase(loanType: string, amount: number): Observable<ApiMessage> {
    const params = new HttpParams()
      .set('loanType', loanType)
      .set('amount', amount.toString());
    return this.http.put<ApiMessage>(`${this.apiUrl}/increase`, null, { params });
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
