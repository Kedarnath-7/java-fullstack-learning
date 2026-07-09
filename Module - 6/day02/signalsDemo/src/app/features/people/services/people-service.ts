import { Injectable, signal, WritableSignal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PeopleService {

  private people: WritableSignal<string[]> = signal([]);

  addPerson(name: string) {
    this.people.set([...this.people(), name]);
  }
  
  getPeople(): WritableSignal<string[]> {
    return this.people;
  }
  
  updatePerson(oldName: string, newName: string) {
    this.people.set(this.people().map((person) => person === oldName ? newName : person));
  }
  
  removePerson(index: number) {
    this.people.set(this.people().filter((_, i) => i !== index));
  }
}
