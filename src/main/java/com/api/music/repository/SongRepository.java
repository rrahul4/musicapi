package com.api.music.repository;

import java.util.Set;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.music.model.Song;

public interface SongRepository extends JpaRepository<Song, Long>{
	Song findByName(String name);
	
	@Query("select id, name from Song")
	Set<Song> getAllNameId();
	
	@Query(nativeQuery = true, value = "SELECT * FROM Song WHERE year > 2000 ORDER BY played DESC LIMIT 20")
	Set<Song> getTrendingSongs(Pageable pageable);
	
	@Query("SELECT u FROM Song u WHERE u.year < 2000 ORDER BY u.played DESC")
	Set<Song> getTrendingOldSongs(Pageable pageable);
	
	@Query("SELECT u FROM Song u WHERE u.year = YEAR(CURRENT_DATE())")
	Set<Song> getLatestSongs(Pageable pageable);
	
	@Query("SELECT u FROM Song u WHERE u.year = ?1")
	Set<Song> getNewSongs(int year, Pageable pageable);
}
