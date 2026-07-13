import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../.././../features/customer/services/auth';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(public authService: AuthService, private router: Router) {}

  getCartCount(): number {
    const stored = localStorage.getItem('cart');
    if (!stored) return 0;
    const items: { productId: number; quantity: number }[] = JSON.parse(stored);
    return items.reduce((sum, i) => sum + i.quantity, 0);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
