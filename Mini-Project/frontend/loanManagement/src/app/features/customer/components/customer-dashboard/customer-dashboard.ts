import { Component, inject, OnInit, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { AuthService } from '../../../auth/services/auth.service';
import { CustomerService } from '../../../admin/services/customer.service';
import { LoanAccountService } from '../../services/loan-account.service';
import { Loading } from '../../../shared/components/loading/loading';

@Component({
  selector: 'app-customer-dashboard',
  imports: [RouterLink, CurrencyPipe, Loading],
  templateUrl: './customer-dashboard.html',
  styleUrl: './customer-dashboard.css',
})
export class CustomerDashboard implements OnInit {
  auth = inject(AuthService);
  customerService = inject(CustomerService);
  loanService = inject(LoanAccountService);

  activeCount = computed(() => this.loanService.loanAccounts().filter(l => l.loanStatus === 'ACTIVE').length);
  recentLoans = computed(() => this.loanService.loanAccounts().slice(0, 5));

  ngOnInit() {
    const userId = this.auth.currentUser()?.customerId;
    if (userId) {
      this.customerService.getSummary(userId);
    }
    this.loanService.getAll();
  }
}
