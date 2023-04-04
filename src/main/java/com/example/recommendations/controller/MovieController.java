package com.example.recommendations.controller;


import com.example.recommendations.dto.MovieDto;
import com.example.recommendations.entities.Movie;
import com.example.recommendations.service.MovieService;
import com.example.recommendations.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private RecommendationService recommendationService;



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(consumes = { "application/json", "multipart/form-data" },
            value = "/save")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDto movieDto) throws IOException {

        return ResponseEntity.ok(movieService.saveMovie(movieDto));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(consumes = "multipart/form-data",
            value = "/image/{id}")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Movie> uploadImage(@PathVariable String id, @RequestParam MultipartFile file) {

        return ResponseEntity.ok(movieService.uploadFile(id, file));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id){

        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name){

        return ResponseEntity.ok(movieService.getMovieByName(name));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String genre){

        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    @GetMapping("/year")
    public ResponseEntity<List<Movie>> getMoviesSortedByYear(){

        return ResponseEntity.ok(recommendationService.getNewestMovies());
    }

    @GetMapping("/rating")
    public ResponseEntity<List<Movie>> getPopularMovies(){

        return ResponseEntity.ok(recommendationService.getPopularMovies());
    }
}
