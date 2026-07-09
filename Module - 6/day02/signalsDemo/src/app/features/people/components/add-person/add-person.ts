import { Component, inject } from '@angular/core';
import { PeopleService } from '../../services/people-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-person',
  imports: [FormsModule],
  templateUrl: './add-person.html',
  styleUrl: './add-person.css',
})
export class AddPerson {
  peopleService: PeopleService = inject(PeopleService);
  protected name: string = '';

  addPerson() {
    this.peopleService.addPerson(this.name);
    this.name = '';
  }
}
