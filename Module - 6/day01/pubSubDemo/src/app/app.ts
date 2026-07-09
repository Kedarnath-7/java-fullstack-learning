import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ShowCount } from "./features/count/components/show-count/show-count";
import { Increment } from "./features/count/components/increment/increment";
import { Decrement } from "./features/count/components/decrement/decrement";
import { IncrementBy } from "./features/count/components/increment-by/increment-by";
import { DecrementBy } from './features/count/components/decrement-by/decrement-by';
import { ShowAll } from "./features/people/components/show-all/show-all";
import { AddPerson } from "./features/people/components/add-person/add-person";
import { UpdatePerson } from "./features/people/components/update-person/update-person";
import { AddMovie } from "./features/movies/components/add-movie/add-movie";
import { ShowAllMovies } from "./features/movies/components/show-all-movies/show-all-movies";
import { UpdateMovie } from "./features/movies/components/update-movie/update-movie";

@Component({
  selector: 'app-root',
  imports: [ShowCount, Increment, Decrement, IncrementBy, DecrementBy, ShowAll, AddPerson, UpdatePerson, AddMovie, ShowAllMovies, UpdateMovie],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

}
