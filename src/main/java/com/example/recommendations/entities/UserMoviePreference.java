package com.example.recommendations.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_movie_preferences",
        uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "movie_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMoviePreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JoinColumn(name = "user_id")
    private UserData user;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;



}
