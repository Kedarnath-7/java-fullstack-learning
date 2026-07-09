import { Component, signal } from '@angular/core';
import { People } from './features/people/components/people/people';
import { PersonComponent } from './features/people/components/person/person';

@Component({
  selector: 'app-root',
  imports: [People],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('secondDemo');
}
