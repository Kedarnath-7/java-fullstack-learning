import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink, Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoanProductService } from '../../services/loan-product.service';
import { LoanProductRequest } from '../../../../core/models';

@Component({
  selector: 'app-loan-product-form',
  imports: [RouterLink, FormsModule],
  templateUrl: './loan-product-form.html',
  styleUrl: './loan-product-form.css',
})
export class LoanProductForm implements OnInit {
  productService = inject(LoanProductService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  isEdit = signal(false);
  loading = signal(false);
  error = signal<string | null>(null);
  editCode = '';

  form: LoanProductRequest = { loanCode: '', loanName: '', loanType: '', interestRate: 0, dailyPenaltyRate: 0 };

  ngOnInit() {
    const code = this.route.snapshot.paramMap.get('code');
    if (code) {
      this.isEdit.set(true);
      this.editCode = code;
      // Find from existing loaded products
      const existing = this.productService.products().find(p => p.loanCode === code);
      if (existing) {
        this.form = { ...existing };
      }
    }
  }

  onSubmit() {
    this.loading.set(true);
    this.error.set(null);
    const obs = this.isEdit()
      ? this.productService.update(this.editCode, this.form)
      : this.productService.create(this.form);
    obs.subscribe({
      next: () => { this.loading.set(false); this.router.navigate(['/admin/loan-products']); },
      error: (err) => { this.loading.set(false); this.error.set(err.userMessage || 'Operation failed'); }
    });
  }
}
