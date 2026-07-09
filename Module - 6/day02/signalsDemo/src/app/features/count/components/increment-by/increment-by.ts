import { Component, inject } from '@angular/core';
import { CountService } from '../../services/count-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-increment-by',
  imports: [FormsModule],
  templateUrl: './increment-by.html',
  styleUrl: './increment-by.css',
})
export class IncrementBy {
  protected value: number = 0;
  countService: CountService = inject(CountService);
}
