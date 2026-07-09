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
  protected name: string = '';
  peopleService: PeopleService = inject(PeopleService);

  addPerson(): void {
    if(this.name.trim() !== ''){
      this.peopleService.addName(this.name);
    }
    this.name = '';
  }
}
