import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  auth = inject(AuthService);
  email = '';
  password = '';

  onSubmit() {
    if (this.email && this.password) {
      this.auth.login({ email: this.email, password: this.password });
    }
  }

  fillCredentials(email: string, password: string) {
    this.email = email;
    this.password = password;
  }
}
