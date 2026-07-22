import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  standalone: true,
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">
          <i class="bi bi-music-note-beamed me-2"></i>
          Music Track Manager
        </a>
      </div>
    </nav>
  `,
})
export class NavbarComponent {}
