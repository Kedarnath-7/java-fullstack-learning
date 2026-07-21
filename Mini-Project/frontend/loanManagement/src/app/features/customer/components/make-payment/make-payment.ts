import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { EmiPaymentService } from '../../services/emi-payment.service';
import { LoanAccountService } from '../../services/loan-account.service';
import { EmiPaymentRequest } from '../../../../core/models';

@Component({
  selector: 'app-make-payment',
  imports: [RouterLink, FormsModule, CurrencyPipe],
  templateUrl: './make-payment.html',
  styleUrl: './make-payment.css',
})
export class MakePayment implements OnInit {
  emiService = inject(EmiPaymentService);
  loanService = inject(LoanAccountService);
  router = inject(Router);

  loading = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  activeLoans = computed(() => this.loanService.loanAccounts().filter(l => l.loanStatus === 'ACTIVE' || l.loanStatus === 'OVERDUE'));

  form: EmiPaymentRequest = {
    amountPaid: 0,
    penaltyPaid: 0,
    paymentType: '',
    paymentDate: new Date().toISOString().split('T')[0],
    loanAccountId: 0
  };

  ngOnInit() {
    this.loanService.getAll();
  }

  onSubmit() {
    this.loading.set(true);
    this.error.set(null);
    this.emiService.create(this.form).subscribe({
      next: () => {
        this.loading.set(false);
        this.success.set(true);
        setTimeout(() => this.router.navigate(['/customer/my-loans']), 2000);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.userMessage || 'Payment failed');
      }
    });
  }
}
