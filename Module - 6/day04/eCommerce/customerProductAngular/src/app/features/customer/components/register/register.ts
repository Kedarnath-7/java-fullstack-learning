import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth';
import { AuthRegisterRequest } from '../../../../shared/models/auth.model';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  registerData: AuthRegisterRequest = { fName: '', lName: '', email: '', password: '' };
  confirmPassword = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister(): void {
    this.errorMessage = '';
    if (this.registerData.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match';
      return;
    }
    this.authService.register(this.registerData).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => {
        this.errorMessage = err.error?.message || 'Registration failed. Please try again.';
      }
    });
  }
}
