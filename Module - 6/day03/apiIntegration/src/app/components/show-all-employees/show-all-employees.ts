import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { EmployeeService } from '../../services/employee-service';
import EmployeeResponseDTO from '../../types/EmployeeResponseDTO';
import { ShowEmployee } from "../show-employee/show-employee";

@Component({
  selector: 'app-show-all-employees',
  imports: [ShowEmployee],
  templateUrl: './show-all-employees.html',
  styleUrl: './show-all-employees.css',
})
export class ShowAllEmployees implements OnInit {
  employees: WritableSignal<EmployeeResponseDTO[]> = signal([]);
  employeeService: EmployeeService = inject(EmployeeService);
  status: WritableSignal<{loading: boolean, error: string, success: boolean}> = signal({loading: false, error: '', success: false});

  ngOnInit(): void {
    this.status.set({
      loading: true,
      error: '',
      success: false
    });
    this.getAllEmployees();
    
  }

  getAllEmployees() {
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.status.set({
          loading: false,
          error: '',
          success: true
        });
        this.employees.set(data);
        console.log(data);
      },
      error: (err) => {
        this.status.set({
          loading: false,
          error: err.message,
          success: false
        });
        console.error(err);
      },
      complete: () => {
        console.log('Complete');
      }
    });
  }

  onEmployeeDeleted(employeeId: number) {
    this.employees.update((list) => list.filter((employee) => employee.id !== employeeId));
  }

}
