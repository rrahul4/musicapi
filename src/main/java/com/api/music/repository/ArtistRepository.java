package com.api.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.music.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Artist findByName(String name);
}
