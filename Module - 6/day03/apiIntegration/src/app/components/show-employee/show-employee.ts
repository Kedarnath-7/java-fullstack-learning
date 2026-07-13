import { Component, EventEmitter, inject, Input, Output, signal, WritableSignal } from '@angular/core';
import EmployeeDTO from '../../types/EmployeeDTO';
import { EmployeeService } from '../../services/employee-service';

@Component({
  selector: 'app-show-employee',
  imports: [],
  templateUrl: './show-employee.html',
  styleUrl: './show-employee.css',
})
export class ShowEmployee {
  employeeService: EmployeeService = inject(EmployeeService);
  @Input() employee!: EmployeeDTO;
  @Output() deleted = new EventEmitter<number>();
  status: WritableSignal<{loading: boolean, error?: string, success?: boolean}> = signal({loading: false, error: '', success: false});

  remove(){
    this.status.set({
      loading: true,
      error: '',
      success: false
    });
    if (this.employee.projects?.length) {
      this.status.set({
        loading: false,
        error: 'Cannot delete employee with assigned projects. Remove assignments first.',
        success: false,
      });
      return;
    }

    if(this.employee && this.employee.id) {
    this.employeeService.deleteEmployee(this.employee.id).subscribe({
      next: (data) => {
        this.status.set({
          loading: false,
          error: '',
          success: true
        });
        this.deleted.emit(this.employee.id);
        console.log(data);
      },
      error: (err) => {
        this.status.set({
          loading: false,
          error: err?.error?.message || err.message || 'Delete failed due to server error.',
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

   
}
