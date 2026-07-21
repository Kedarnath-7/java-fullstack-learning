import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-customer-list',
  imports: [RouterLink, Loading, EmptyState],
  templateUrl: './customer-list.html',
  styleUrl: './customer-list.css',
})
export class CustomerList implements OnInit {
  customerService = inject(CustomerService);
  deleteLoading = signal<number | null>(null);

  ngOnInit() {
    this.customerService.getAll();
  }

  deleteCustomer(id: number) {
    if (!confirm('Are you sure you want to delete this customer?')) return;
    this.deleteLoading.set(id);
    this.customerService.delete(id).subscribe({
      next: () => { this.deleteLoading.set(null); this.customerService.getAll(); },
      error: () => this.deleteLoading.set(null)
    });
  }
}
