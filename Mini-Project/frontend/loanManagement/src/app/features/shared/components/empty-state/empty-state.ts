import { Component, input } from '@angular/core';

@Component({
  selector: 'app-empty-state',
  imports: [],
  templateUrl: './empty-state.html',
  styles: ``
})
export class EmptyState {
  icon = input('bi-inbox');
  title = input('No Data');
  message = input('There is nothing to display here yet.');
}
