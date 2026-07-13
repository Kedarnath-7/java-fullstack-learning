import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import EmployeeDTO from '../types/EmployeeDTO';
import EmployeeRequestDTO from '../types/EmployeeRequestDTO';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  httpClient: HttpClient = inject(HttpClient);

  getAllEmployees(): Observable<EmployeeDTO[]> {
    return this.httpClient.get<EmployeeDTO[]>('/api/employees');
  }

  getEmployeeById(id: number): Observable<EmployeeDTO> {
    return this.httpClient.get<EmployeeDTO>(`/api/employees/${id}`);
  }

  addEmployee(employee: EmployeeRequestDTO): Observable<EmployeeDTO> {
    return this.httpClient.post<EmployeeDTO>('/api/employees', employee);
  }

  updateEmployee(id: number, employee: EmployeeDTO): Observable<EmployeeDTO> {
    return this.httpClient.put<EmployeeDTO>(`/api/employees/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.httpClient.delete<void>(`/api/employees/${id}`);
  }

  deleteAllEmployees(): Observable<void> {
    return this.httpClient.delete<void>('/api/employees');
  }

  getEmployeesByDepartment(dept: string): Observable<EmployeeDTO[]> {
    return this.httpClient.get<EmployeeDTO[]>(`/api/employees?dept=${dept}`);
  }

}
