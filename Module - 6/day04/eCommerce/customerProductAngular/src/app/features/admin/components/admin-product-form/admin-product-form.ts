import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../../product/services/product';
import { Product } from '../../../../shared/models/product.model';

@Component({
  selector: 'app-admin-product-form',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './admin-product-form.html',
  styleUrl: './admin-product-form.css',
})
export class AdminProductForm implements OnInit {
  isEdit = false;
  productId: number | null = null;
  formData: Partial<Product> = { name: '', brand: '', category: '', cost: 0, stock: 0 };
  errorMessage = signal('');

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.productId = Number(id);
      this.productService.getById(this.productId).subscribe({
        next: (data) => {
          this.formData = { name: data.name, brand: data.brand, category: data.category, cost: data.cost, stock: data.stock };
        },
        error: (err) => console.error('Failed to load product', err)
      });
    }
  }

  onSubmit(): void {
    this.errorMessage.set('');
    if (this.isEdit && this.productId) {
      this.productService.update(this.productId, this.formData as Product).subscribe({
        next: () => this.router.navigate(['/admin/products']),
        error: (err) => this.errorMessage.set(err.error?.message || 'Update failed')
      });
    } else {
      this.productService.add(this.formData as Product).subscribe({
        next: () => this.router.navigate(['/admin/products']),
        error: (err) => this.errorMessage.set(err.error?.message || 'Add failed')
      });
    }
  }
}
