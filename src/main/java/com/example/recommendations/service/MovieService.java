package com.example.recommendations.service;


import com.example.recommendations.dto.MovieDto;
import com.example.recommendations.entities.Movie;
import com.example.recommendations.exceptions.AppException;
import com.example.recommendations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RecommendationService recommendationService;


    public Movie saveMovie(MovieDto movieDto) throws IOException {

        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setAuthor(movieDto.getAuthor());
        movie.setGenre(movieDto.getGenre());
        movie.setYear(movieDto.getYear());
        movie.setRating(movieDto.getRating());

        return movieRepository.save(movie);
    }

    public Movie uploadFile(String id,MultipartFile file){

        Movie movie = movieRepository.findById(Long.parseLong(id))
                .orElseThrow(()->(new AppException("Movie with id " + id + "doesn't exist", HttpStatus.NOT_FOUND)));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));


        try {
            // Save the file to the local directory
            String path = FileUploadUtil.saveFile(fileName, file);
            movie.setMedia(path + "\\" + fileName);
            movieRepository.save(movie);
        } catch (IOException ex) {
            throw new AppException("error", HttpStatus.BAD_REQUEST);
        }

        return movie;

    }

    public Movie getMovieById(String id){


        return movieRepository.findById(Long.parseLong(id))
                .orElseThrow(()->(new AppException("Movie with id " + id + "doesn't exist", HttpStatus.NOT_FOUND)));
    }


    public Movie getMovieByName(String name){

        return movieRepository.findByName(name)
                 .orElseThrow(()->(new AppException("Movie with id " + name + "doesn't exist", HttpStatus.NOT_FOUND)));
    }

    public List<Movie> getMovieByGenre(String genre){

        return movieRepository.findByGenre(genre);
    }


}
