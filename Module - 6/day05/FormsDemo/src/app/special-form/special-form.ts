import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-special-form',
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './special-form.html',
  styleUrl: './special-form.css',
})
export class SpecialForm {
  newCustomer = {
    name: '',
    age: 0,
  }

  // handleSubmit() {
  //   console.log('Form submitted:', this.newCustomer);
  // }

  handleSubmit(specialForm: NgForm) {
    if(specialForm.valid) {
      console.log('Form submitted:', this.newCustomer);
    } else {
      console.log('Form is invalid. Please correct the errors.');
    }
  }
}
