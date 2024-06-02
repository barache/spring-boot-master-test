package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(uses = ActeurMapper.class)
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "acteurs", target = "acteurs")
    FilmDto filmToFilmDTO(Film film);

    @Mapping(source = "titre", target = "titre")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "acteurs", target = "acteurs")
    Film filmDTOToFilm(FilmDto filmDto);

}