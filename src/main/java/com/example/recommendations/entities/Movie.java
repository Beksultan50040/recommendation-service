package com.example.recommendations.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String author;

    @NotBlank
    private String genre;

//    @Lob
//    private byte[] image;

//    @OneToMany(mappedBy = "movie")
//    @JsonManagedReference
//    @EqualsAndHashCode.Exclude @ToString.Exclude
//    private List<UserMoviePreference> preferences;


}