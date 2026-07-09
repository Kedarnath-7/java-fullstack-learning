import { Component, inject } from '@angular/core';
import { MovieService } from '../../services/movie-service';


@Component({
  selector: 'app-show-all-movies',
  imports: [],
  templateUrl: './show-all-movies.html',
  styleUrl: './show-all-movies.css',
})
export class ShowAllMovies {
  movieService: MovieService = inject(MovieService);
}
