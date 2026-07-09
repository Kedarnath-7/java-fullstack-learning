import { Component, inject } from '@angular/core';
import { MovieService } from '../../services/movie-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-movie',
  imports: [FormsModule],
  templateUrl: './update-movie.html',
  styleUrl: './update-movie.css',
})
export class UpdateMovie {
  movieService: MovieService = inject(MovieService);
  protected movie = {
    id: 0,
    title: '',
    genre: '',
    rating: 0
  };

  updateMovie(){
    this.movieService.updateMovie(this.movie);
    this.movie = {
      id: 0,
      title: '',
      genre: '',
      rating: 0
    };
  }
}
