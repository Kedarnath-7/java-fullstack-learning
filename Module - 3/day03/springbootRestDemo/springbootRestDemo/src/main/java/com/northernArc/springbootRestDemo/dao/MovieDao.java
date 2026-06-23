package com.northernArc.springbootRestDemo.dao;

import com.northernArc.springbootRestDemo.model.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieDao {
    void addMovie(Movie movie);
    void updateMovieById(int id, Movie movie);
    void deleteMovieById(int id);
    Movie findMovieById(int id);
    Collection<Movie> findAllMovies();
    void deleteAllMovies();

}
