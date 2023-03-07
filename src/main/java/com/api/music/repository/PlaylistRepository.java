package com.api.music.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.music.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	@Query("select u from Playlist u where u.userid = ?1 and u.name = ?2")
	Playlist getPlaylistByPlaylistNameAndUserId(long userid, String playlistname);
	
	@Query("select u from Playlist u where u.userid = ?1")
	Set<Playlist> getPlaylistsForUser(long userid);
}
