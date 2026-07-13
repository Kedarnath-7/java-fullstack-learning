import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../../../shared/models/product.model';

const BASE_URL = 'http://localhost:8080/api/products';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(BASE_URL);
  }

  getById(id: number): Observable<Product> {
    return this.http.get<Product>(`${BASE_URL}/${id}`);
  }

  search(name?: string, brand?: string, category?: string): Observable<Product[]> {
    let params = new HttpParams();
    if (name) params = params.set('name', name);
    if (brand) params = params.set('brand', brand);
    if (category) params = params.set('category', category);
    return this.http.get<Product[]>(`${BASE_URL}/search`, { params });
  }

  add(product: Product): Observable<Product> {
    return this.http.post<Product>(BASE_URL, product);
  }

  update(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${BASE_URL}/${id}`, product);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/${id}`);
  }
}
