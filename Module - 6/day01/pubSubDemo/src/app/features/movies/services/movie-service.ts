import { Injectable } from '@angular/core';
import MovieDTO from '../../../types/MovieDTO';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private movies: MovieDTO[] = [
    { id: 1, title: 'Inception', genre: 'Sci-Fi', rating: 8.8 },
    { id: 2, title: 'The Dark Knight', genre: 'Action', rating: 9.0 },
    { id: 3, title: 'Interstellar', genre: 'Sci-Fi', rating: 8.6 },
    { id: 4, title: 'The Matrix', genre: 'Sci-Fi', rating: 8.7 },
    { id: 5, title: 'Pulp Fiction', genre: 'Crime', rating: 8.9 },
  ];

  private movies$: BehaviorSubject<MovieDTO[]> = new BehaviorSubject<MovieDTO[]>(this.movies);

  getMovies(): BehaviorSubject<MovieDTO[]> {
    return this.movies$;
  }

  addMovie(movie: MovieDTO): void {
    this.movies$.next([...this.movies, movie]);
  }

  removeMovie(id: number): void {
    this.movies$.next(this.movies.filter(movie => movie.id !== id));
  }

  updateMovie(updatedMovie: MovieDTO): void {
    this.movies$.next(this.movies.map(movie => movie.id === updatedMovie.id ? updatedMovie : movie));
  }
}
