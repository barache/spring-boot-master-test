package com.example.testspringboot.repository;

import com.example.testspringboot.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    @Query("SELECT f FROM Film AS f WHERE f.id = :id")
    Film foundFilmById(@Param("id") Long id);
}
