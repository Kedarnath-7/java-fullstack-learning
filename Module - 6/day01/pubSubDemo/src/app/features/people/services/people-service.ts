import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PeopleService {
  private names: string[] = ['Alice', 'Bob', 'Charlie', 'David', 'Eve'];
  private names$: BehaviorSubject<string[]> = new BehaviorSubject<string[]>(this.names);

  getNames(): BehaviorSubject<string[]> {
    return this.names$;
  }

  addName(name: string): void {
    this.names = [...this.names, name];
    this.names$.next([...this.names]);
  }

  removeName(name: string): void {
    this.names = this.names.filter(n => n !== name);
    this.names$.next([...this.names]);
  }

  updateName(oldName: string, newName: string): void {
    const index = this.names.indexOf(oldName);
    if (index !== -1) {
      this.names[index] = newName;
      this.names$.next(this.names);
    }

    // this.names = this.names.map(n => n === oldName ? newName : n);
    // this.names$.next([...this.names]);
  }
}
