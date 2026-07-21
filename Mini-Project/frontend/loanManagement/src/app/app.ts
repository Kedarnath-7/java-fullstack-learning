import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from './features/shared/components/navbar/navbar';
import { Footer } from './features/shared/components/footer/footer';
import { ThemeService } from './core/services/theme.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  // Initialize theme service at app start
  private themeService = inject(ThemeService);
}
