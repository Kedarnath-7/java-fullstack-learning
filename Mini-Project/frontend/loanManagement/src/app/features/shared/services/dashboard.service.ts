import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { DashboardResponse } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  private apiUrl = `${environment.apiUrl}/dashboard`;

  data = signal<DashboardResponse | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  load() {
    this.loading.set(true);
    this.error.set(null);
    this.http.get<DashboardResponse>(this.apiUrl).subscribe({
      next: (data) => {
        this.data.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load dashboard');
        this.loading.set(false);
      }
    });
  }
}
