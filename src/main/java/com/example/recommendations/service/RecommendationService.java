package com.example.recommendations.service;


import com.example.recommendations.entities.Movie;
import com.example.recommendations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {


    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getNewestMovies(){

        return movieRepository.findAllByOrderByYearDesc();
    }



    public List<Movie> getPopularMovies(){

        return movieRepository.findAllByOrderByRatingDesc();
    }
}
