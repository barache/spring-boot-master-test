package com.example.testspringboot.service;


import com.example.testspringboot.Utils.Constants;
import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class FilmServiceTest {

    @InjectMocks
    private FilmService filmService;
    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

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


    @Test
    public void createFilmTest() {

        Mockito.when(filmRepository.save(film)).thenReturn(film);

        Film result = filmService.createFilm(film);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Constants.TITRE, result.getTitre());
        assertEquals(Constants.DESCRIPTION, result.getDescription());
        assertEquals(result.getActeurs().size(), 2);

        Mockito.verify(filmRepository, Mockito.times(1)).save(film);
    }

    @Test
    public void getFilmTest() {

        Long id = 1L;

        Mockito.when(filmRepository.findById(id)).thenReturn(Optional.ofNullable(film));

        Mockito.when(filmMapper.filmToFilmDTO(film)).thenReturn(filmDto);

        FilmDto result = filmService.getFilm(id);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Constants.TITRE, result.getTitre());
        assertEquals(Constants.DESCRIPTION, result.getDescription());
        assertEquals(result.getActeurs().size(), 2);

        Mockito.verify(filmRepository, Mockito.times(1)).findById(id);
    }

}
