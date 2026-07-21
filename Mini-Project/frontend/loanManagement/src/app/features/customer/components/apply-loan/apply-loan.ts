import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoanProductService } from '../../../admin/services/loan-product.service';
import { LoanAccountService } from '../../services/loan-account.service';
import { AuthService } from '../../../auth/services/auth.service';
import { LoanAccountRequest } from '../../../../core/models';

@Component({
  selector: 'app-apply-loan',
  imports: [RouterLink, FormsModule],
  templateUrl: './apply-loan.html',
  styleUrl: './apply-loan.css',
})
export class ApplyLoan implements OnInit {
  productService = inject(LoanProductService);
  loanService = inject(LoanAccountService);
  auth = inject(AuthService);
  router = inject(Router);

  loading = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form: LoanAccountRequest = {
    loanStartDate: new Date().toISOString().split('T')[0],
    emiDueDate: '',
    loanAmount: 0,
    emiAmount: 0,
    loanCode: ''
  };

  ngOnInit() {
    this.productService.getAll(0, 50);
  }

  onSubmit() {
    this.loading.set(true);
    this.error.set(null);
    this.loanService.create(this.form).subscribe({
      next: () => {
        this.loading.set(false);
        this.success.set(true);
        setTimeout(() => this.router.navigate(['/customer/my-loans']), 2000);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.userMessage || 'Failed to submit application');
      }
    });
  }
}
