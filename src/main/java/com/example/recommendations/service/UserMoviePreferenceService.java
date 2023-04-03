package com.example.recommendations.service;


import com.example.recommendations.entities.Movie;
import com.example.recommendations.entities.UserData;
import com.example.recommendations.entities.UserMoviePreference;
import com.example.recommendations.exceptions.AppException;
import com.example.recommendations.repository.MovieRepository;
import com.example.recommendations.repository.UserDataRepository;
import com.example.recommendations.repository.UserMoviePreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserMoviePreferenceService {

    @Autowired
    private UserMoviePreferenceRepository userMoviePreferenceRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void savePreferences(String userId, String movieId){

        UserData userData = userDataRepository.findById(Long.valueOf(userId)).orElseThrow(() ->
                new AppException("Unknown user with id " + userId, HttpStatus.NOT_FOUND));

        Movie movie = movieRepository.findById(Long.valueOf(movieId)).orElseThrow(() ->
                new AppException("Unknown movie with id " + movieId, HttpStatus.NOT_FOUND));



        UserMoviePreference userMoviePreference = new UserMoviePreference();
        userMoviePreference.setUser(userData);
        userMoviePreference.setMovie(movie);

        userMoviePreferenceRepository.save(userMoviePreference);

    }
}
