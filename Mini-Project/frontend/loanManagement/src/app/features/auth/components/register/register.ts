import { Component, inject, signal } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { CustomerResponse } from '../../../../core/models';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  private http = inject(HttpClient);
  private router = inject(Router);

  loading = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = {
    customerName: '',
    email: '',
    password: '',
    confirmPassword: '',
    branch: '',
    role: 'USER'
  };

  onSubmit() {
    if (this.form.password !== this.form.confirmPassword) {
      this.error.set('Passwords do not match');
      return;
    }
    this.loading.set(true);
    this.error.set(null);

    const { confirmPassword, ...payload } = this.form;
    this.http.post<CustomerResponse>(`${environment.apiUrl}/customers`, payload).subscribe({
      next: () => {
        this.loading.set(false);
        this.success.set(true);
        setTimeout(() => this.router.navigate(['/auth/login']), 2000);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.userMessage || 'Registration failed');
      }
    });
  }
}
