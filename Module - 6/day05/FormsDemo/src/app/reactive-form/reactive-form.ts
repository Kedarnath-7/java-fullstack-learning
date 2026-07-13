import { JsonPipe, NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-reactive-form',
  imports: [ReactiveFormsModule, NgIf, JsonPipe],
  templateUrl: './reactive-form.html',
  styleUrl: './reactive-form.css',
})
export class ReactiveForm {

  private formBuilder: FormBuilder = inject(FormBuilder);

  customerForm = this.formBuilder.group({
    name: new FormControl('', Validators.required),
    age: new FormControl<number | null>(0, [Validators.required, Validators.min(0)]),
  });
}
