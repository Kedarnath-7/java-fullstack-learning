import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../../customer/services/customer';
import { CustomerResponseDTO } from '../../../../shared/models/customer.model';

@Component({
  selector: 'app-admin-customers',
  imports: [RouterLink, CommonModule],
  templateUrl: './admin-customers.html',
  styleUrl: './admin-customers.css',
})
export class AdminCustomers implements OnInit {
  customers = signal<CustomerResponseDTO[]>([]);

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getAll().subscribe({
      next: (data) => this.customers.set(data),
      error: (err) => console.error('Failed to load customers', err)
    });
  }

  deleteCustomer(id: number): void {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.customerService.delete(id).subscribe({
        next: () => this.loadCustomers(),
        error: (err) => console.error('Failed to delete customer', err)
      });
    }
  }
}
