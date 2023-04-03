package com.example.recommendations.dto;

import com.example.recommendations.entities.UserCredentials;
import com.example.recommendations.entities.UserMoviePreference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDataDto {



    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private List<UserMoviePreferenceDto> preferences;

}
