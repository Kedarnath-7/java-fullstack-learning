import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CountService {
  private count$: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  increment(): void {
    const currentCount = this.count$.value;
    this.count$.next(currentCount + 1);
  }

  decrement(): void {
    const currentCount = this.count$.value;
    this.count$.next(currentCount - 1);
  }

  getCount(): BehaviorSubject<number> {
    return this.count$;
  }

  incrementBy(value: number): void {
    const currentCount = this.count$.value;
    this.count$.next(currentCount + value);
  }

  decrementBy(value: number): void {
    const currentCount = this.count$.value;
    this.count$.next(currentCount - value);
  }

}
