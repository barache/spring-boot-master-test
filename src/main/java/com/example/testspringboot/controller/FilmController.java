package com.example.testspringboot.controller;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.ResourceNotFoundException;
import com.example.testspringboot.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/film/")
public class FilmController {

    private final FilmService filmService;

    @GetMapping("{id}")
    public ResponseEntity<FilmDto>  getFilm(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(filmService.getFilm(id));
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmService.createFilm(film));
    }

    @GetMapping
    public ResponseEntity<List<Film>>  getAllFilm(){
        return ResponseEntity.ok(filmService.getAllFilm());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("id") Long id){
        filmService.deleteFilm(id);
        return new ResponseEntity<>("Film supprimé avec succès!", HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable("id") Long id,
                                           @RequestBody Film film){
        film.setId(id);
        Film updatedFilm = filmService.updateFilm(id, film);
        return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
    }


}
