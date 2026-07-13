import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth';
import { AuthLoginRequest } from '../../../../shared/models/auth.model';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginData: AuthLoginRequest = { email: '', password: '' };
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.errorMessage = '';
    this.authService.login(this.loginData).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => {
        this.errorMessage = err.error?.message || 'Invalid email or password';
      }
    });
  }
}
