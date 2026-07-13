import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../services/order';
import { OrderResponseDTO } from '../../../../shared/models/order.model';

@Component({
  selector: 'app-order-detail',
  imports: [RouterLink, CommonModule],
  templateUrl: './order-detail.html',
  styleUrl: './order-detail.css',
})
export class OrderDetail implements OnInit {
  order = signal<OrderResponseDTO | null>(null);
  cancelSuccess = signal(false);
  errorMessage = signal('');

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.orderService.getById(id).subscribe({
        next: (data) => this.order.set(data),
        error: (err) => console.error('Failed to load order', err)
      });
    }
  }

  cancelOrder(): void {
    const o = this.order();
    if (!o) return;
    this.orderService.cancel(o.order_id).subscribe({
      next: () => this.cancelSuccess.set(true),
      error: (err) => this.errorMessage.set(err.error?.message || 'Failed to cancel order')
    });
  }
}
