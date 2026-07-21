import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { LoanAccountService } from '../../services/loan-account.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-my-loans',
  imports: [RouterLink, FormsModule, CurrencyPipe, Loading, EmptyState],
  templateUrl: './my-loans.html',
  styleUrl: './my-loans.css',
})
export class MyLoans implements OnInit {
  loanService = inject(LoanAccountService);

  filterStatus = signal('');
  filterType = signal('');
  searchId = signal('');

  filteredLoans = computed(() => {
    let loans = this.loanService.loanAccounts();
    const status = this.filterStatus();
    const type = this.filterType();
    const id = this.searchId();
    if (status) loans = loans.filter(l => l.loanStatus === status);
    if (type) loans = loans.filter(l => l.loanType === type);
    if (id) loans = loans.filter(l => l.loanAccountId.toString().includes(id));
    return loans;
  });

  ngOnInit() {
    this.loanService.getAll();
  }
}
