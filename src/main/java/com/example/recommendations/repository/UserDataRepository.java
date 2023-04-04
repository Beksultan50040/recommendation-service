package com.example.recommendations.repository;

import com.example.recommendations.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Long > {

    UserData findById(long id);


//    @Query("SELECT u, m FROM UserData u JOIN UserMoviePreference p ON u.id = p.user JOIN Movie m ON p.movie = m.id WHERE u.id = :id")
//    UserData findUserAndMoviePreferencesById(@Param("id") Long id);
}
