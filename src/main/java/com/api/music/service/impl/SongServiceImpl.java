package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.music.model.Artist;
import com.api.music.model.Genre;
import com.api.music.model.Musician;
import com.api.music.model.Playlist;
import com.api.music.model.Song;
import com.api.music.repository.SongRepository;
import com.api.music.service.BaseService;

@Service
public class SongServiceImpl implements BaseService<Song> {

	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private ArtistServiceImpl artistService;
	
	@Autowired
	private GenreServiceImpl genreService;
	
	@Autowired
	private PlaylistServiceImpl playlistService;
	
	@Autowired
	private MusicianServiceImpl musicianService;
	
	@Override
	public Page<Song> getAll(int pageno) {
		PageRequest request = PageRequest.of(pageno,20);
		return songRepository.findAll(request);
	}
	
	public Page<Song> getAll(PageRequest request) {
		return songRepository.findAll(request);
	}
	

	@Override
	public Song getById(long id) {
		return songRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Song> getByIds(Set<Long> ids) {
		return new HashSet<Song>(songRepository.findAllById(ids));
	}
	
	public Song getByName(String name) {
		return songRepository.findByName(name);
	}
	
	public Set<Song> getAllNameId(){
		return songRepository.getAllNameId();
	}

	@Override
	public Song update(Song obj) {
		//never update id, played, likes, 
		if(obj.getId() == 0) return null;
		
		Song song = songRepository.findById(obj.getId()).orElse(null);
		/*
		if(song == null) return null;
		
		song.setName(obj.getName() == null ? song.getName() : obj.getName());
		//song.setAlbum(obj.getAlbum() == null ? song.getAlbum() : obj.getAlbum());
		
		if(obj.getAlbum() == null) {
			System.out.println("it is null = " + obj.getName());
		}
		
		song.setDate(obj.getDate() == null ? song.getDate() : obj.getDate());
		song.setDuration(obj.getDuration() == 0 ? song.getDuration() : obj.getDuration());
		song.setYear(obj.getYear() == 0 ? song.getYear() : obj.getYear());
		*/
		/*
		for(Artist a : obj.getArtists()) {
			song.setArtist(artistService.getById(a.getId()));
		}
		
		for(Genre g : obj.getGenres()) {
			song.setGenre(genreService.getById(g.getId()));
		}
		
		for(Playlist p : obj.getPlaylists()) {
			song.setPlaylist(playlistService.getById(p.getId()));
		}
		
		for(Musician m: obj.getMusicBy()) {
			song.setMusician(musicianService.getById(m.getId()));
		}
		*/

		
		return songRepository.save(obj);
	}

	@Override
	public void delete(long id) {
		Song song = songRepository.findById(id).orElse(null);
		if(song != null) {
			songRepository.delete(song);
		}
		
	}

	@Override
	public Song add(Song obj) {
		if(obj == null) return null;
		if(obj.getId() != 0) return null;
		
		obj.clearAllRelation();
		
		/*
		if(obj.getAlbum() != null) {
			obj.setAlbum(albumServiceImpl.getById(obj.getAlbum().getId()));
		}*/
		
		obj.setAlbum(null);
		
		return songRepository.save(obj);
	}
	
	
	public Song mapArtists(long songid,Set<Long> artistids) {
		if(songid == 0) return null;
		
		Song song = getById(songid);
		
		if(song == null) return null;
		
		for(long id: artistids) {
			song.setArtist(artistService.getById(id));
		}
		
		return songRepository.save(song);
	}
	
	public Song mapPlaylists(long songid,Set<Long> playlistids) {
		if(songid == 0) return null;
		
		Song song = getById(songid);
		
		if(song == null) return null;
		
		for(long id: playlistids) {
			song.setPlaylist(playlistService.getById(id));
		}
		
		return songRepository.save(song);
	}
	
	public Song mapMusicians(long songid,Set<Long> playlistids) {
		if(songid == 0) return null;
		
		Song song = getById(songid);
		
		if(song == null) return null;
		
		for(long id: playlistids) {
			song.setMusician(musicianService.getById(id));
		}
		
		return songRepository.save(song);
	}
	
	
	public Song mapGenre(long songid,Set<Long> genreids) {
		if(songid == 0) return null;
		
		Song song = getById(songid);
		
		if(song == null) return null;
		
		for(long id: genreids) {
			song.setGenre(genreService.getById(id));
		}
		
		return songRepository.save(song);
	}
	
	public Page<Song> getTrendingSongs(int pageno){
		PageRequest request = PageRequest.of(pageno,20);
		
		return songRepository.getTrendingSongs(request);
	}
	
	public Page<Song> getOldTrendingSongs(int pageno){
		PageRequest request = PageRequest.of(pageno,20);
		return songRepository.getTrendingOldSongs(request);
	}
	
	public Page<Song> getlatestSongs(int pageno){
		
		PageRequest request = PageRequest.of(pageno,20, Sort.by(Sort.Direction.DESC, "year", "played"));
		return songRepository.findAll(request);
	}
	
	public Page<Song> getNewSongs(int year, int pageno){
		PageRequest request = PageRequest.of(pageno,20);
		return songRepository.getNewSongs(year, request);
	}
}
