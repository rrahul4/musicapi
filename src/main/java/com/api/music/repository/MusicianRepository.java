package com.api.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.music.model.Musician;

public interface MusicianRepository extends JpaRepository<Musician, Long> {

}
