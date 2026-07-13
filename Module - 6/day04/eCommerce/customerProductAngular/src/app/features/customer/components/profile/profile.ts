import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer';
import { AuthService } from '../../services/auth';
import { CustomerResponseDTO, CustomerRequestDTO } from '../../../../shared/models/customer.model';

@Component({
  selector: 'app-profile',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  customer = signal<CustomerResponseDTO | null>(null);
  editData = { fName: '', lName: '', email: '', password: '' };
  successMessage = signal('');
  errorMessage = signal('');

  constructor(
    private customerService: CustomerService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const customerId = this.authService.getCustomerId();
    if (customerId) {
      this.customerService.getById(customerId).subscribe({
        next: (data) => {
          this.customer.set(data);
          this.editData = { fName: data.fName, lName: data.lName, email: this.authService.getCustomerEmail() || '', password: '' };
        },
        error: (err) => console.error('Failed to load profile', err)
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  onUpdate(): void {
    this.successMessage.set('');
    this.errorMessage.set('');
    const customerId = this.authService.getCustomerId();
    if (!customerId) return;

    const request: CustomerRequestDTO = {
      fName: this.editData.fName,
      lName: this.editData.lName,
      email: this.editData.email,
      password: this.editData.password
    };

    this.customerService.update(customerId, request).subscribe({
      next: (data) => {
        this.customer.set(data);
        this.successMessage.set('Profile updated successfully!');
      },
      error: (err) => {
        this.errorMessage.set(err.error?.message || 'Update failed');
      }
    });
  }

  onDeleteAccount(): void {
    const customerId = this.authService.getCustomerId();
    if (!customerId) return;

    this.customerService.delete(customerId).subscribe({
      next: () => {
        this.authService.logout();
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.errorMessage.set(err.error?.message || 'Delete failed');
      }
    });
  }

  getInitials(): string {
    const c = this.customer();
    if (!c) return '';
    return (c.fName?.charAt(0) || '') + (c.lName?.charAt(0) || '');
  }
}
