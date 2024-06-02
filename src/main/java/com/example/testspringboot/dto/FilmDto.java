package com.example.testspringboot.dto;

import com.example.testspringboot.entity.Acteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDto {
    private Long id;
    private String titre;
    private String description;
    private Collection<Acteur> acteurs;
}
