package com.example.recommendations.controller;

import com.example.recommendations.entities.UserMoviePreference;
import com.example.recommendations.service.UserMoviePreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preference")
public class UserMoviePreferenceController {

    @Autowired
    private UserMoviePreferenceService userMoviePreferenceService;


    @PostMapping("")
    public void saveUserMoviePreference(@RequestParam String userId, @RequestParam String movieId){

        userMoviePreferenceService.savePreferences(userId, movieId);
    }
}
