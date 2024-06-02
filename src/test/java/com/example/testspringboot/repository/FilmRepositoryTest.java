package com.example.testspringboot.repository;

import com.example.testspringboot.Utils.Constants;
import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class FilmRepositoryTest {

    @Mock
    private FilmRepository filmRepository;

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

    @Test
    public void getFilmByQueryTest() {

        Long id = 1L;

        Mockito.when(filmRepository.foundFilmById(id)).thenReturn(film);

        Film result = filmRepository.foundFilmById(id);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Constants.TITRE, result.getTitre());
        assertEquals(Constants.DESCRIPTION, result.getDescription());
        assertEquals(result.getActeurs().size(), 2);

        Mockito.verify(filmRepository, Mockito.times(1)).foundFilmById(id);    }
}
