package com.api.music.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.music.model.Album;
import com.api.music.model.Song;
import com.api.music.service.impl.AlbumServiceImpl;
import com.api.music.service.impl.SongServiceImpl;

@RestController
public class MappingController {
	
	@Autowired
	SongServiceImpl songService;
	
	@Autowired
	AlbumServiceImpl albumService;
	
	
	@PutMapping("api/v1/song/mapArtists/{songid}")
	public Song mapSongtoArtists(@PathVariable long songid, @RequestBody Set<Long> artistids) {
		return songService.mapArtists(songid, artistids);
	}
	
	@PutMapping("api/v1/song/mapGenre/{songid}")
	public Song mapSongtoGenres(@PathVariable long songid, @RequestBody Set<Long> genreids) {
		return songService.mapGenre(songid, genreids);
	}
	
	@PutMapping("api/v1/song/mapPlaylist/{songid}")
	public Song mapSongtoPlaylists(@PathVariable long songid, @RequestBody Set<Long> playlistids) {
		return songService.mapPlaylists(songid, playlistids);
	}
	
	@PutMapping("api/v1/song/mapMusicians/{songid}")
	public Song mapSongtoMusicby(@PathVariable long songid, @RequestBody Set<Long> musicianids) {
		return songService.mapMusicians(songid, musicianids);
	}
	
	@PutMapping("api/v1/album/mapSongs/{albumid}")
	public Album mapAlbumtoSong(@PathVariable long albumid, @RequestBody Set<Long> songids) {
		return albumService.mapSongs(albumid, songids);
	}
	
	
}
