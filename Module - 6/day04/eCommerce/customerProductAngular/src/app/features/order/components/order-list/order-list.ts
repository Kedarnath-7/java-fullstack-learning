import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../services/order';
import { OrderResponseDTO } from '../../../../shared/models/order.model';

@Component({
  selector: 'app-order-list',
  imports: [RouterLink, CommonModule],
  templateUrl: './order-list.html',
  styleUrl: './order-list.css',
})
export class OrderList implements OnInit {
  orders = signal<OrderResponseDTO[]>([]);

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.orderService.getMyOrders().subscribe({
      next: (data) => this.orders.set(data),
      error: (err) => console.error('Failed to load orders', err)
    });
  }

  cancelOrder(id: number): void {
    this.orderService.cancel(id).subscribe({
      next: () => this.loadOrders(),
      error: (err) => console.error('Failed to cancel order', err)
    });
  }
}
