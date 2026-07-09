import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PersonDTO } from '../../../../types/person-dto';

@Component({
  selector: 'app-person-form',
  imports: [FormsModule],
  templateUrl: './add-person.html',
  styleUrl: './add-person.css',
})
export class PersonFormComponent {
  // @Output()
  // onAdd = new EventEmitter<PersonDTO>();
  
  @Output()
  save = new EventEmitter<PersonDTO>();

  @Input()
  person?: PersonDTO;

  @Input()
  isEditing = false;
  
  p: PersonDTO = {
    id: 0,
    name: '',
    role: '',
    age: 0,
  };

  // add() {
  //   this.onAdd.emit({ ...this.p });

  //   this.p = {
  //     id: 0,
  //     name: '',
  //     role: '',
  //     age: 0
  //   };
  // }

  savePerson() {
    this.save.emit({ ...this.p });
  }
  
}
