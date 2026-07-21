import { Component, inject, OnInit } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { LoanAccountService } from '../../services/loan-account.service';
import { Loading } from '../../../shared/components/loading/loading';

@Component({
  selector: 'app-loan-detail',
  imports: [RouterLink, CurrencyPipe, Loading],
  templateUrl: './loan-detail.html',
  styleUrl: './loan-detail.css',
})
export class LoanDetail implements OnInit {
  loanService = inject(LoanAccountService);
  private route = inject(ActivatedRoute);

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) this.loanService.getById(id);
  }
}
