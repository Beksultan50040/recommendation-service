package com.example.recommendations.dto;


import com.example.recommendations.entities.Movie;
import com.example.recommendations.entities.UserData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMoviePreferenceDto {

    private Movie movie;

}
