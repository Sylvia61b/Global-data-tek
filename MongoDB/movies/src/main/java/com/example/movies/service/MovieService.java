package com.example.movies.service;

import com.example.movies.Movie;
import com.example.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

//    @Autowired
//    public MovieService(MovieRepository movieRepository){
//        this.movieRepository = movieRepository;
//    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
