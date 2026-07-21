import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { DashboardService } from '../../../shared/services/dashboard.service';
import { Loading } from '../../../shared/components/loading/loading';

@Component({
  selector: 'app-admin-dashboard',
  imports: [RouterLink, CurrencyPipe, Loading],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css',
})
export class AdminDashboard implements OnInit {
  dashboardService = inject(DashboardService);

  ngOnInit() {
    this.dashboardService.load();
  }
}
