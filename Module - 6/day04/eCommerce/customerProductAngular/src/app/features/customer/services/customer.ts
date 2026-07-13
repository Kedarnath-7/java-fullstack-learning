import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerRequestDTO, CustomerResponseDTO } from '../../../shared/models/customer.model';

const BASE_URL = 'http://localhost:8080/api/customers';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<CustomerResponseDTO[]> {
    return this.http.get<CustomerResponseDTO[]>(BASE_URL);
  }

  getById(id: number): Observable<CustomerResponseDTO> {
    return this.http.get<CustomerResponseDTO>(`${BASE_URL}/${id}`);
  }

  add(customer: CustomerRequestDTO): Observable<CustomerResponseDTO> {
    return this.http.post<CustomerResponseDTO>(BASE_URL, customer);
  }

  update(id: number, customer: CustomerRequestDTO): Observable<CustomerResponseDTO> {
    return this.http.put<CustomerResponseDTO>(`${BASE_URL}/${id}`, customer);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/${id}`);
  }
}
