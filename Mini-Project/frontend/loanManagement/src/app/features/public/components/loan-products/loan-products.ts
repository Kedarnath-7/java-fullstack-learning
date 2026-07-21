import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoanProductService } from '../../../admin/services/loan-product.service';
import { Loading } from '../../../shared/components/loading/loading';
import { EmptyState } from '../../../shared/components/empty-state/empty-state';

@Component({
  selector: 'app-loan-products',
  imports: [RouterLink, Loading, EmptyState],
  templateUrl: './loan-products.html',
  styleUrl: './loan-products.css',
})
export class LoanProducts implements OnInit {
  productService = inject(LoanProductService);

  ngOnInit() {
    this.productService.getAll(0, 50);
  }
}
