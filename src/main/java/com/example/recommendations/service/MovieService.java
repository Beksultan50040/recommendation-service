package com.example.recommendations.service;


import com.example.recommendations.entities.Movie;
import com.example.recommendations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;


    public Movie saveMovie(String name, String author,
                           String genre /*MultipartFile image*/) throws IOException {

        Movie movie = new Movie();
        movie.setName(name);
        movie.setAuthor(author);
        movie.setGenre(genre);
//        movie.setImage(image.getBytes());
        return movieRepository.save(movie);
    }

    public Movie getMovie(String id){

        Movie movie = movieRepository.findById(Long.parseLong(id));
        return movieRepository.findById(Long.parseLong(id));
    }
}
