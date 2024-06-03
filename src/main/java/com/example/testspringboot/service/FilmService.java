package com.example.testspringboot.service;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.ResourceNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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

        return filmMapper.filmToFilmDTO(film);
    }

    public Film createFilm(Film film) {
        filmRepository.save(film);
        return film;
    }

    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public Film updateFilm(Long id, Film updatedFilm) {

        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND, id)));

        BeanUtils.copyProperties(updatedFilm, existingFilm, "id");
        return filmRepository.save(existingFilm);
    }

}