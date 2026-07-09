import { Component, inject } from '@angular/core';
import { PeopleService } from '../../services/people-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-person',
  imports: [FormsModule],
  templateUrl: './update-person.html',
  styleUrl: './update-person.css',
})
export class UpdatePerson {
  protected oldName: string = '';
  protected newName: string = '';

  peopleService: PeopleService = inject(PeopleService);

  updatePerson(): void {
    if(this.oldName.trim() !== '' && this.newName.trim() !== ''){
      this.peopleService.updateName(this.oldName, this.newName);
    }
    this.oldName = '';
    this.newName = '';
  }
}
