import { Component } from '@angular/core';
import { PersonComponent } from '../person/person';
import { PersonDTO } from '../../../../types/person-dto';
import { PersonFormComponent } from "../add-person/person-form";

@Component({
  selector: 'app-people',
  imports: [PersonComponent, PersonFormComponent],
  templateUrl: './people.html',
  styleUrl: './people.css',
})
export class People {

  selectedPerson!: PersonDTO;
  isEditing = false;
  
  people: PersonDTO[] = [
    { id: 1, name: 'MS Dhoni', role: 'WK-Batsman', age: 43 },
    { id: 2, name: 'Ruturaj Gaikwad', role: 'Batsman', age: 29 },
    { id: 3, name: 'Sanju Samson', role: 'WK-Batsman', age: 33 },
  ];

  removePerson(id: number) {
    this.people = this.people.filter((person) => person.id !== id);
  }

  addPerson(person: PersonDTO) {
    this.people = [...this.people, person];
    // this.people.push(person);
  }

  editPerson(person: PersonDTO) {
    this.selectedPerson = { ...person };
    this.isEditing = true;
  }

}
