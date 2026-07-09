import { Component, inject } from '@angular/core';
import { MovieService } from '../../services/movie-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-movie',
  imports: [FormsModule],
  templateUrl: './add-movie.html',
  styleUrl: './add-movie.css',
})
export class AddMovie {
  protected title: string = '';
  protected genre: string = ''
  protected rating: number = 0;
  protected id: number = 0;
  movieService: MovieService = inject(MovieService);
  addMovie(){
    this.movieService.addMovie({
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
