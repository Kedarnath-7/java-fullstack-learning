import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { LoanProductRequest, LoanProductResponse, ApiMessage, PageResponse } from '../../../core/models';

@Injectable({ providedIn: 'root' })
export class LoanProductService {
  private apiUrl = `${environment.apiUrl}/loan-products`;

  products = signal<LoanProductResponse[]>([]);
  selectedProduct = signal<LoanProductResponse | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);
  success = signal<string | null>(null);
  totalPages = signal(0);
  currentPage = signal(0);

  constructor(private http: HttpClient) {}

  getAll(page = 0, size = 10, sort = 'loanCode') {
    this.loading.set(true);
    this.error.set(null);
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);

    this.http.get<PageResponse<LoanProductResponse>>(this.apiUrl, { params }).subscribe({
      next: (data) => {
        this.products.set(data.content);
        this.totalPages.set(data.totalPages);
        this.currentPage.set(data.number);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set(err.userMessage || 'Failed to load loan products');
        this.loading.set(false);
      }
    });
  }

  create(product: LoanProductRequest): Observable<LoanProductResponse> {
    return this.http.post<LoanProductResponse>(this.apiUrl, product);
  }

  update(loanCode: string, product: LoanProductRequest): Observable<LoanProductResponse> {
    return this.http.put<LoanProductResponse>(`${this.apiUrl}/${loanCode}`, product);
  }

  delete(loanCode: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${loanCode}`);
  }

  updatePenaltyRate(loanCode: string, amount: number): Observable<ApiMessage> {
    return this.http.put<ApiMessage>(
      `${this.apiUrl}/${loanCode}/penalty-rate`,
      null,
      { params: new HttpParams().set('amount', amount.toString()) }
    );
  }

  clearMessages() {
    this.error.set(null);
    this.success.set(null);
  }
}
