package com.example.recommendations.repository;

import com.example.recommendations.entities.UserData;
import com.example.recommendations.entities.UserMoviePreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMoviePreferenceRepository extends JpaRepository<UserMoviePreference, Long> {


    UserMoviePreference findAllByUserId(UserData userId);
}
