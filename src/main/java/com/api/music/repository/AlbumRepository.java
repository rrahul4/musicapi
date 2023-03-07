package com.api.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.music.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	Album findByName(String name);
}
