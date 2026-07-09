import { Component, inject } from '@angular/core';
import { CountService } from '../../services/count-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-decrement-by',
  imports: [FormsModule],
  templateUrl: './decrement-by.html',
  styleUrl: './decrement-by.css',
})
export class DecrementBy {
  countService: CountService = inject(CountService);
decrementAmount: number = 0;
}
