package com.example.testspringboot.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import com.example.testspringboot.Utils.Constants;
import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @MockBean
    private FilmService filmService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFilmTestReturnStatusIsCreated() throws Exception {

        Film film =
                Film.builder()
                        .id(1L)
                        .titre(Constants.TITRE)
                        .description(Constants.DESCRIPTION)
                        .acteurs(Arrays.asList(
                                Acteur.builder()
                                        .id(1L)
                                        .nom(Constants.NOM_ACTEUR_1)
                                        .prenom(Constants.PRENOM_ACTEUR_1)
                                        .build(),
                                Acteur.builder()
                                        .id(2L)
                                        .nom(Constants.NOM_ACTEUR_2)
                                        .prenom(Constants.PRENOM_ACTEUR_2)
                                        .build()
                        ))
                        .build();

        mockMvc.perform(post("/api/film/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(film)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnFilm() throws Exception {
        long id = 1L;

        FilmDto filmDto =
                FilmDto.builder()
                        .id(1L)
                        .titre(Constants.TITRE)
                        .description(Constants.DESCRIPTION)
                        .acteurs(Arrays.asList(
                                Acteur.builder()
                                        .id(1L)
                                        .nom(Constants.NOM_ACTEUR_1)
                                        .prenom(Constants.PRENOM_ACTEUR_1)
                                        .build(),
                                Acteur.builder()
                                        .id(2L)
                                        .nom(Constants.NOM_ACTEUR_2)
                                        .prenom(Constants.PRENOM_ACTEUR_2)
                                        .build()
                        ))
                        .build();


        when(filmService.getFilm(id)).thenReturn(filmDto);
        mockMvc.perform(get("/api/film/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.titre").value(filmDto.getTitre()))
                .andExpect(jsonPath("$.description").value(filmDto.getDescription()))
                .andDo(print());
    }

}
