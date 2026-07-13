import { Component, OnInit, signal } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product';
import { Product } from '../../../../shared/models/product.model';

@Component({
  selector: 'app-product-detail',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.css',
})
export class ProductDetail implements OnInit {
  product = signal<Product | null>(null);
  quantity = signal(1);
  addedToCart = signal(false);

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.productService.getById(id).subscribe({
        next: (data) => this.product.set(data),
        error: (err) => console.error('Failed to load product', err)
      });
    }
  }

  increaseQty(): void {
    const p = this.product();
    if (p && this.quantity() < p.stock) {
      this.quantity.update(q => q + 1);
    }
  }

  decreaseQty(): void {
    if (this.quantity() > 1) {
      this.quantity.update(q => q - 1);
    }
  }

  addToCart(): void {
    const p = this.product();
    if (!p) return;
    const stored = localStorage.getItem('cart');
    let items: { productId: number; quantity: number }[] = stored ? JSON.parse(stored) : [];
    const existing = items.find(i => i.productId === p.product_id);
    if (existing) {
      existing.quantity += this.quantity();
    } else {
      items.push({ productId: p.product_id, quantity: this.quantity() });
    }
    localStorage.setItem('cart', JSON.stringify(items));
    this.addedToCart.set(true);
    setTimeout(() => this.addedToCart.set(false), 2000);
  }
}
