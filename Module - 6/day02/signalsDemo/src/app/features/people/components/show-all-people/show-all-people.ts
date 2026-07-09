import { Component, inject } from '@angular/core';
import { PeopleService } from '../../services/people-service';

@Component({
  selector: 'app-show-all-people',
  imports: [],
  templateUrl: './show-all-people.html',
  styleUrl: './show-all-people.css',
})
export class ShowAllPeople {
  peopleService: PeopleService = inject(PeopleService);
}
