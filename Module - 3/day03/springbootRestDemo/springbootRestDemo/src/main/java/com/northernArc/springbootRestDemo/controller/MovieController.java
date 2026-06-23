package com.northernArc.springbootRestDemo.controller;

import com.northernArc.springbootRestDemo.dao.MovieDao;
import com.northernArc.springbootRestDemo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieDao movieDao;

    @RequestMapping("")
    public Collection<Movie> getAllMovies() {
        return movieDao.findAllMovies();
    }

    @RequestMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieDao.findMovieById(id);
    }

    @RequestMapping("/delete/{id}")
    public Collection<Movie> deleteMovieById(@PathVariable int id){
        movieDao.deleteMovieById(id);
        return movieDao.findAllMovies();
    }

    @RequestMapping("/add/{id}-{title}-{director}-{releaseYear}-{genre}-{rating}")
    public Collection<Movie> addMovieById(@PathVariable int id, @PathVariable String title, @PathVariable String director, @PathVariable int releaseYear, @PathVariable String genre, @PathVariable double rating) {
        movieDao.addMovie(new Movie(id, title, director, releaseYear, genre, rating));
        return movieDao.findAllMovies();
    }

    // request param
    @RequestMapping("/add")
    public void addMovie(@RequestParam Integer id, @RequestParam String title,
                         @RequestParam String director, @RequestParam Integer releaseYear,
                         @RequestParam String genre, @RequestParam Double rating) {
        movieDao.addMovie(new Movie(id, title, director, releaseYear, genre, rating));
    }



    @RequestMapping("/update/{}")
    public Collection<Movie> updateMovies(@RequestBody Collection<Movie> movies) {

        return movieDao.findAllMovies();
    }



}
