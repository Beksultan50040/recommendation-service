package com.example.recommendations.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDataDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;

    private List<UserMoviePreferenceDto> preferences;

}
