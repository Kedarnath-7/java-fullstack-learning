import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-normal-form',
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './normal-form.html',
  styleUrl: './normal-form.css',
})
export class NormalForm {
  newCustomer = {
    name: '',
    age: 0,
  }
}
