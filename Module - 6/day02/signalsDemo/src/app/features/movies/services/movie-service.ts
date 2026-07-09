import { Injectable, signal, WritableSignal } from '@angular/core';
import MovieDTO from '../../../types/MovieDTO';

@Injectable({
  providedIn: 'root',
})
export class MovieService {

  private movies: WritableSignal<MovieDTO[]> = signal([]);

  addMovie(movie: MovieDTO): void {
    this.movies.set([...this.movies(), movie]);
  }

  getMovies(): WritableSignal<MovieDTO[]> {
    return this.movies;
  }

  updateMovie(updatedMovie: MovieDTO): void {
    this.movies.set(this.movies().map((movie) =>
      movie.id === updatedMovie.id ? updatedMovie : movie
    ));
  }

  deleteMovie(movieId: number): void {
    this.movies.set(this.movies().filter((movie) => movie.id !== movieId));
  }
}
