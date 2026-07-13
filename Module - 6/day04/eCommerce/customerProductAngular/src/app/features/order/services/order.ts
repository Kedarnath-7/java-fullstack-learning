import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderRequestDTO, OrderResponseDTO } from '../../../shared/models/order.model';

const BASE_URL = 'http://localhost:8080/api/orders';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<OrderResponseDTO[]> {
    return this.http.get<OrderResponseDTO[]>(BASE_URL);
  }

  getMyOrders(): Observable<OrderResponseDTO[]> {
    return this.http.get<OrderResponseDTO[]>(`${BASE_URL}/my`);
  }

  getById(id: number): Observable<OrderResponseDTO> {
    return this.http.get<OrderResponseDTO>(`${BASE_URL}/${id}`);
  }

  create(order: OrderRequestDTO): Observable<OrderResponseDTO> {
    return this.http.post<OrderResponseDTO>(BASE_URL, order);
  }

  update(id: number, order: OrderRequestDTO): Observable<OrderResponseDTO> {
    return this.http.put<OrderResponseDTO>(`${BASE_URL}/${id}`, order);
  }

  cancel(id: number): Observable<string> {
    return this.http.put(`${BASE_URL}/${id}/cancel`, null, { responseType: 'text' });
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/${id}`);
  }
}
