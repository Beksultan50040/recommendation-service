package com.example.recommendations.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_data")
public class UserData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @NotBlank
//    @Email
    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonManagedReference
    private List<UserMoviePreference> preferences;

//    @OneToOne
//    @EqualsAndHashCode.Exclude @ToString.Exclude
//    @JoinColumn(name = "user_credentials_id")
//    private UserCredentials userCredentials;
}
