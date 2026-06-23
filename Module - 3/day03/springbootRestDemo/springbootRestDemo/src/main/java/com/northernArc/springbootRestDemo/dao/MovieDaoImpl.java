package com.northernArc.springbootRestDemo.dao;

import com.northernArc.springbootRestDemo.model.Movie;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieDaoImpl implements MovieDao {
    private Map<Integer, Movie> movieCollection;

    @PostConstruct
    public void init() {
        movieCollection = new LinkedHashMap<Integer, Movie>();
        movieCollection.put(1, new Movie(1, "Durandhar", "Aditya Dhar", 2025, "drama", 9.0));
        movieCollection.put(2, new Movie(2,"Avenger: End game", "Rossu Brothers", 2018, "super heor", 8.4));
        movieCollection.put(3, new Movie(3, "Game Changer", "Shankar", 2024, "whoknows", 3.2));
    }
    @Override
    public void addMovie(Movie movie) {
        movieCollection.put(movie.getId(), movie);
    }

    @Override
    public void updateMovieById(int id, Movie movie) {
        movieCollection.put(id, movie);
    }

    @Override
    public void deleteMovieById(int id) {
        movieCollection.remove(id);
    }

    @Override
    public Movie findMovieById(int id) {
        return movieCollection.get(id);
    }

    @Override
    public Collection<Movie> findAllMovies() {
        return movieCollection.values();
    }

    @Override
    public void deleteAllMovies() {
        movieCollection.clear();
    }
}
