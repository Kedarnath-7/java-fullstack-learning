import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoanAccountService } from '../../../customer/services/loan-account.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-all-loans',
  imports: [RouterLink, CurrencyPipe, FormsModule, Loading, EmptyState],
  templateUrl: './all-loans.html',
  styleUrl: './all-loans.css',
})
export class AllLoans implements OnInit {
  loanService = inject(LoanAccountService);
  filterStatus = signal('');
  deleteLoading = signal<number | null>(null);

  filteredLoans = computed(() => {
    const status = this.filterStatus();
    if (!status) return this.loanService.loanAccounts();
    return this.loanService.loanAccounts().filter(l => l.loanStatus === status);
  });

  ngOnInit() { this.loanService.getAll(); }

  deleteLoan(id: number) {
    if (!confirm(`Delete loan #${id}?`)) return;
    this.deleteLoading.set(id);
    this.loanService.delete(id).subscribe({
      next: () => { this.deleteLoading.set(null); this.loanService.getAll(); },
      error: () => this.deleteLoading.set(null)
    });
  }

  closeLoan(id: number) {
    if (!confirm(`Close loan #${id}?`)) return;
    this.loanService.close(id).subscribe({ next: () => this.loanService.getAll() });
  }
}
