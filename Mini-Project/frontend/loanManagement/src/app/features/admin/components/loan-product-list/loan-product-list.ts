import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoanProductService } from '../../services/loan-product.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-loan-product-list',
  imports: [RouterLink, Loading, EmptyState],
  templateUrl: './loan-product-list.html',
  styleUrl: './loan-product-list.css',
})
export class LoanProductList implements OnInit {
  productService = inject(LoanProductService);
  deleteLoading = signal<string | null>(null);

  ngOnInit() { this.productService.getAll(); }

  deleteProduct(code: string) {
    if (!confirm(`Delete product ${code}?`)) return;
    this.deleteLoading.set(code);
    this.productService.delete(code).subscribe({
      next: () => { this.deleteLoading.set(null); this.productService.getAll(); },
      error: () => this.deleteLoading.set(null)
    });
  }
}
