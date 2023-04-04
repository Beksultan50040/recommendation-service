package com.example.recommendations.repository;

import com.example.recommendations.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Long> {


    Optional<UserCredentials> findByEmail(String login);





}
