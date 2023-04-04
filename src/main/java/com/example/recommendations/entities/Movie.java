package com.example.recommendations.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

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

    @NotNull
    private Integer year;

    private String media;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;



//    @OneToMany(mappedBy = "movie")
//    @JsonManagedReference
//    @EqualsAndHashCode.Exclude @ToString.Exclude
//    private List<UserMoviePreference> preferences;


}