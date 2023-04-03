package com.example.recommendations.controller;


import com.example.recommendations.entities.Movie;
import com.example.recommendations.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/movie/save")
    public Movie saveMovie(@RequestParam String name,
                           @RequestParam String author,
                           @RequestParam String genre
    //                       @RequestParam MultipartFile image
    ) throws IOException {

        return movieService.saveMovie(name,author,genre/*image*/);
    }

    @GetMapping("")
    public Movie getMovie(@RequestParam String id){



        return movieService.getMovie(id);
    }
}
