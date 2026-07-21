import { Component, inject } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-unauthorized',
  imports: [RouterLink],
  templateUrl: './unauthorized.html',
  styles: ``,
})
export class Unauthorized {
  private location = inject(Location);

  goBack() {
    this.location.back();
  }
}
