import { Component, input } from '@angular/core';

@Component({
  selector: 'app-loading',
  imports: [],
  templateUrl: './loading.html',
  styles: ``
})
export class Loading {
  message = input('Loading...');
}
