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
  protected title: string = '';
  protected genre: string = '';
  protected rating: number = 0;
  protected id: number = 0;

  movieService: MovieService = inject(MovieService);
  
  updateMovie(){
    this.movieService.updateMovie({
      id: this.id,
      title: this.title,
      genre: this.genre,
      rating: this.rating
    });
    this.title = '';
    this.genre = '';
    this.rating = 0;
    this.id = 0;
  }
}
