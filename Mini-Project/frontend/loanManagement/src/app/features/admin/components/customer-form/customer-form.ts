import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink, Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { CustomerRequest } from '../../../../core/models';

@Component({
  selector: 'app-customer-form',
  imports: [RouterLink, FormsModule],
  templateUrl: './customer-form.html',
  styleUrl: './customer-form.css',
})
export class CustomerForm implements OnInit {
  customerService = inject(CustomerService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  isEdit = signal(false);
  loading = signal(false);
  error = signal<string | null>(null);
  customerId = 0;

  form: CustomerRequest = { customerName: '', email: '', password: '', branch: '', role: 'USER' };

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit.set(true);
      this.customerId = Number(id);
      this.customerService.getById(this.customerId);
      // Pre-fill form when data loads (simplified - in real app use effect)
      setTimeout(() => {
        const c = this.customerService.selectedCustomer();
        if (c) {
          this.form.customerName = c.customerName;
          this.form.email = c.email;
          this.form.branch = c.branch;
          this.form.role = c.role;
        }
      }, 1000);
    }
  }

  onSubmit() {
    this.loading.set(true);
    this.error.set(null);
    const obs = this.isEdit()
      ? this.customerService.update(this.customerId, this.form)
      : this.customerService.create(this.form);

    obs.subscribe({
      next: () => { this.loading.set(false); this.router.navigate(['/admin/customers']); },
      error: (err) => { this.loading.set(false); this.error.set(err.userMessage || 'Operation failed'); }
    });
  }
}
