package com.api.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.music.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
