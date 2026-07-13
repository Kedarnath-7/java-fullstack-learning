import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product';
import { Product } from '../../../../shared/models/product.model';

@Component({
  selector: 'app-product-list',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit {
  products = signal<Product[]>([]);
  searchName = '';
  searchBrand = '';
  searchCategory = '';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAll().subscribe({
      next: (data) => this.products.set(data),
      error: (err) => console.error('Failed to load products', err)
    });
  }

  onSearch(): void {
    const name = this.searchName.trim() || undefined;
    const brand = this.searchBrand || undefined;
    const category = this.searchCategory || undefined;

    if (name || brand || category) {
      this.productService.search(name, brand, category).subscribe({
        next: (data) => this.products.set(data),
        error: (err) => console.error('Search failed', err)
      });
    } else {
      this.loadProducts();
    }
  }
}
