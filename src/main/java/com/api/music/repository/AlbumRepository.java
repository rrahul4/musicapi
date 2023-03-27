package com.api.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.music.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	List<Album> findByName(String name);
}
