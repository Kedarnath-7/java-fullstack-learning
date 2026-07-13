import { Component, inject, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import EmployeeRequestDTO from '../../types/EmployeeRequestDTO';
import { EmployeeService } from '../../services/employee-service';

@Component({
  selector: 'app-add-employee',
  imports: [FormsModule],
  templateUrl: './add-employee.html',
  styleUrl: './add-employee.css',
})
export class AddEmployee {
  employeeService: EmployeeService = inject(EmployeeService);
  status: WritableSignal<{loading: boolean, error: string, success: boolean}> = signal({loading: false, error: '', success: false});
  employee: EmployeeRequestDTO = {
    name: '',
    email: '',
    dept: '',
  };

  addEmployee() {
    if (!this.employee.name.trim() || !this.employee.email.trim() || !this.employee.dept.trim()) {
      this.status.set({
        loading: false,
        error: 'Please fill all fields before submitting.',
        success: false,
      });
      return;
    }

    this.status.set({
      loading: true,
      error: '',
      success: false
    });
    this.employeeService.addEmployee(this.employee).subscribe({
      next: (data) => {
        this.status.set({
          loading: false,
          error: '',
          success: true
        });
        this.employee = {
          name: '',
          email: '',
          dept: '',
        };
        console.log('Employee added successfully:', data);
      },
      error: (err) => {
        this.status.set({
          loading: false,
          error: err?.error?.message || err.message || 'Failed to add employee.',
          success: false
        });
        console.error(err);
      },
      complete: () => {
        console.log('Complete');
      }
    });
  }
}
