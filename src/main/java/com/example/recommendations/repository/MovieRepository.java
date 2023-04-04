package com.example.recommendations.repository;

import com.example.recommendations.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById(long id);

    Optional<Movie> findByName(String name);
    List<Movie> findByGenre(String genre);

    List<Movie> findAllByOrderByYearDesc();
    List<Movie> findAllByOrderByRatingDesc();
}
