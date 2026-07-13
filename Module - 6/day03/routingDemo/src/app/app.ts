import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [FormsModule, RouterLink, RouterOutlet, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  router = inject(Router);
  searchTerm = signal('');
  showPerson(){
    if(this.searchTerm() == "kedarnath") {
      this.router.navigate(['/person']);
    }
  }
}
