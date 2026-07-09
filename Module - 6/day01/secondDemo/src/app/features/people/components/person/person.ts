import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PersonDTO } from '../../../../types/person-dto';


@Component({
  selector: 'app-person',
  imports: [],
  templateUrl: './person.html',
  styleUrl: './person.css',
})
export class PersonComponent {
  @Input()
  public p!: PersonDTO;

  @Output()
  onRemove = new EventEmitter<void>();

  @Output()
  onEdit = new EventEmitter<PersonDTO>();
  
}
