import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../../product/services/product';
import { OrderService } from '../../services/order';
import { AuthService } from '../../../customer/services/auth';
import { Product } from '../../../../shared/models/product.model';
import { OrderRequestDTO, OrderItemRequestDTO } from '../../../../shared/models/order.model';

interface CartItem {
  product: Product;
  quantity: number;
}

@Component({
  selector: 'app-cart',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './cart.html',
  styleUrl: './cart.css',
})
export class Cart implements OnInit {
  cartItems = signal<CartItem[]>([]);
  orderDate: string = new Date().toISOString().split('T')[0];
  successMessage = signal('');
  errorMessage = signal('');

  constructor(
    private productService: ProductService,
    private orderService: OrderService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCartFromStorage();
  }

  loadCartFromStorage(): void {
    const stored = localStorage.getItem('cart');
    if (stored) {
      const items: { productId: number; quantity: number }[] = JSON.parse(stored);
      const loaded: CartItem[] = [];
      let remaining = items.length;
      items.forEach(item => {
        this.productService.getById(item.productId).subscribe({
          next: (product) => {
            loaded.push({ product, quantity: item.quantity });
            remaining--;
            if (remaining === 0) {
              this.cartItems.set(loaded);
            }
          }
        });
      });
    }
  }

  getSubtotal(item: CartItem): number {
    return item.product.cost * item.quantity;
  }

  getTotal(): number {
    return this.cartItems().reduce((sum, item) => sum + this.getSubtotal(item), 0);
  }

  getTotalQuantity(): number {
    return this.cartItems().reduce((sum, item) => sum + item.quantity, 0);
  }

  increaseQty(item: CartItem): void {
    if (item.quantity < item.product.stock) {
      item.quantity++;
      this.cartItems.set([...this.cartItems()]);
      this.saveCart();
    }
  }

  decreaseQty(item: CartItem): void {
    if (item.quantity > 1) {
      item.quantity--;
      this.cartItems.set([...this.cartItems()]);
      this.saveCart();
    }
  }

  removeItem(index: number): void {
    const items = [...this.cartItems()];
    items.splice(index, 1);
    this.cartItems.set(items);
    this.saveCart();
  }

  clearCart(): void {
    this.cartItems.set([]);
    localStorage.removeItem('cart');
  }

  saveCart(): void {
    const items = this.cartItems().map(ci => ({ productId: ci.product.product_id, quantity: ci.quantity }));
    localStorage.setItem('cart', JSON.stringify(items));
  }

  placeOrder(): void {
    this.successMessage.set('');
    this.errorMessage.set('');
    const customerId = this.authService.getCustomerId();
    if (!customerId) {
      this.router.navigate(['/login']);
      return;
    }

    const orderItems: OrderItemRequestDTO[] = this.cartItems().map(ci => ({
      productId: ci.product.product_id,
      quantity: ci.quantity
    }));

    const request: OrderRequestDTO = {
      orderDate: this.orderDate,
      customerId,
      orderItems
    };

    this.orderService.create(request).subscribe({
      next: (order) => {
        this.clearCart();
        this.successMessage.set(`Order #${order.order_id} placed successfully!`);
      },
      error: (err) => {
        this.errorMessage.set(err.error?.message || 'Failed to place order');
      }
    });
  }
}
