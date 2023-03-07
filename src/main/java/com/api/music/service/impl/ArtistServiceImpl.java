package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.music.model.Artist;
import com.api.music.repository.ArtistRepository;
import com.api.music.service.BaseService;

@Service
public class ArtistServiceImpl implements BaseService<Artist>{

	@Autowired
	private ArtistRepository artistRepository;
	

	@Override
	public Set<Artist> getAll() {
		return new HashSet<Artist>(artistRepository.findAll());
	}

	@Override
	public Artist getById(long id) {
		return artistRepository.findById(id).orElse(null);
	}
	
	public Artist getByName(String name) {
		return artistRepository.findByName(name);
	}

	@Override
	public Set<Artist> getByIds(Set<Long> ids) {
		return new HashSet<Artist>(artistRepository.findAllById(ids));
	}

	@Override
	public Artist update(Artist obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		
		Artist artist = getById(obj.getId());
		
		artist.setAge(obj.getAge() == 0 ? artist.getAge() : obj.getAge());
		artist.setName(obj.getName() == null ? artist.getName() : obj.getName());
		
		/*
		for(Song s: obj.getSongs()) {
			artist.setSong(songService.getById(s.getId()));
		}*/
		
		
		return artistRepository.save(obj);
	}

	@Override
	public void delete(long id) {
		Artist artist = artistRepository.findById(id).orElse(null);
		if(artist != null) {
			artistRepository.delete(artist);
		}
	}

	@Override
	public Artist add(Artist obj) {
		if(obj == null) return null;
		if(obj.getId() > 0) return null;
		
		obj.clearSongs();
		
		return artistRepository.save(obj);
	}
	

}
