import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../../order/services/order';
import { OrderResponseDTO } from '../../../../shared/models/order.model';

@Component({
  selector: 'app-admin-orders',
  imports: [RouterLink, CommonModule],
  templateUrl: './admin-orders.html',
  styleUrl: './admin-orders.css',
})
export class AdminOrders implements OnInit {
  orders = signal<OrderResponseDTO[]>([]);

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.orderService.getAll().subscribe({
      next: (data) => this.orders.set(data),
      error: (err) => console.error('Failed to load orders', err)
    });
  }

  deleteOrder(id: number): void {
    if (confirm('Are you sure you want to delete this order?')) {
      this.orderService.delete(id).subscribe({
        next: () => this.loadOrders(),
        error: (err) => console.error('Failed to delete order', err)
      });
    }
  }
}
