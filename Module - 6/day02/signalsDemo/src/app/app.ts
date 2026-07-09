import { Component, signal } from '@angular/core';
import { ShowCount } from "./features/count/components/show-count/show-count";
import { Decrement } from "./features/count/components/decrement/decrement";
import { Increment } from "./features/count/components/increment/increment";
import { IncrementBy } from "./features/count/components/increment-by/increment-by";
import { DecrementBy } from "./features/count/components/decrement-by/decrement-by";
import { ShowAllMovies } from "./features/movies/components/show-all-movies/show-all-movies";
import { UpdateMovie } from "./features/movies/components/update-movie/update-movie";
import { AddMovie } from "./features/movies/components/add-movie/add-movie";
import { ShowAllPeople } from "./features/people/components/show-all-people/show-all-people";
import { AddPerson } from "./features/people/components/add-person/add-person";
import { UpdatePerson } from "./features/people/components/update-person/update-person";

@Component({
  selector: 'app-root',
  imports: [ShowCount, Decrement, Increment, IncrementBy, DecrementBy, ShowAllMovies, UpdateMovie, AddMovie, ShowAllPeople, AddPerson, UpdatePerson],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('signalsDemo');
}
