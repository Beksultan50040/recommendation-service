package com.example.recommendations.repository;

import com.example.recommendations.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findById(long id);
}
