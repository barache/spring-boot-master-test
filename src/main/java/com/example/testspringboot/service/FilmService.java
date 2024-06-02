package com.example.testspringboot.service;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.ResourceNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    private static final String NOT_FOUND = "Film introuvable avec le id: %s.";

    public FilmDto getFilm(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND, id)));

        FilmDto filmDto = filmMapper.filmToFilmDTO(film);
        return filmDto;
    }

    public Film createFilm(Film film) {
        filmRepository.save(film);
        return film;
    }

    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }

}