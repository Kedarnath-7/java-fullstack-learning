import { Component, inject } from '@angular/core';
import { PeopleService } from '../../services/people-service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-show-all',
  imports: [AsyncPipe],
  templateUrl: './show-all.html',
  styleUrl: './show-all.css',
})
export class ShowAll {

  peopleService: PeopleService = inject(PeopleService);
}
