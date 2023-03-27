package com.api.music.repository;

import java.util.Set;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.music.model.Song;

public interface SongRepository extends JpaRepository<Song, Long>{
	Song findByName(String name);
	
	@Query("select id, name from Song")
	Set<Song> getAllNameId();
	
	@Query(nativeQuery = true, value = "SELECT * FROM Song WHERE year > 2000 ORDER BY played DESC LIMIT 20")
	Page<Song> getTrendingSongs(PageRequest pageable);
	
	@Query("SELECT u FROM Song u WHERE u.year < 2000 ORDER BY u.played DESC")
	Page<Song> getTrendingOldSongs(PageRequest pageable);
	
	/*
	@Query("SELECT u FROM Song u WHERE u.year = YEAR(CURRENT_DATE())")
	Set<Song> getLatestSongs();
	*/
	
	@Query("SELECT u FROM Song u WHERE u.year = ?1")
	Page<Song> getNewSongs(int year, PageRequest amount);
}
