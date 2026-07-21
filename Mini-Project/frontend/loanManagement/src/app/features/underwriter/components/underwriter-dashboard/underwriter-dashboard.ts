import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { LoanApplicationService } from '../../services/loan-application.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-underwriter-dashboard',
  imports: [RouterLink, CurrencyPipe, Loading, EmptyState],
  templateUrl: './underwriter-dashboard.html',
  styleUrl: './underwriter-dashboard.css',
})
export class UnderwriterDashboard implements OnInit {
  appService = inject(LoanApplicationService);
  actionLoading = signal<number | null>(null);

  ngOnInit() {
    this.appService.getAll();
  }

  approve(id: number) {
    this.actionLoading.set(id);
    this.appService.approve(id).subscribe({
      next: () => { this.actionLoading.set(null); this.appService.getAll(); },
      error: () => this.actionLoading.set(null)
    });
  }

  reject(id: number) {
    this.actionLoading.set(id);
    this.appService.reject(id).subscribe({
      next: () => { this.actionLoading.set(null); this.appService.getAll(); },
      error: () => this.actionLoading.set(null)
    });
  }
}
