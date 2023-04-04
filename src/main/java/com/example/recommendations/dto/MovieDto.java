package com.example.recommendations.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {


    private String name;
    private String author;

    private String genre;
    private Integer year;

    private BigDecimal rating;
}