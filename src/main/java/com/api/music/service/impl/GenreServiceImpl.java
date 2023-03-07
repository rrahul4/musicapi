package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.music.model.Genre;
import com.api.music.repository.GenreRepository;
import com.api.music.service.BaseService;

@Service
public class GenreServiceImpl implements BaseService<Genre>{

	@Autowired
	private GenreRepository genreRepository;


	@Override
	public Set<Genre> getAll() {
		return new HashSet<Genre>(genreRepository.findAll());
	}

	@Override
	public Genre getById(long id) {
		return genreRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Genre> getByIds(Set<Long> ids) {
		return new HashSet<Genre>(genreRepository.findAllById(ids));
	}

	@Override
	public Genre update(Genre obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		
		Genre genre = getById(obj.getId());
		if(genre == null) return null; 
		
		genre.setName(obj.getName() == null ? genre.getName() : obj.getName());
		genre.setDiscription(obj.getDiscription() == null ? genre.getDiscription() : obj.getDiscription());
		
		
		/*
		for(Song s: genre.getSongs()) {
			genre.setSong(songService.getById(s.getId()));
		}*/
		
		return genreRepository.save(genre);
	}

	@Override
	public void delete(long id) {
		if(id == 0) return;
		genreRepository.delete(getById(id));
	}

	@Override
	public Genre add(Genre obj) {
		if(obj == null) return null;
		if(obj.getId() > 0) return null;
		
		obj.clearSongs();
		return genreRepository.save(obj);
	}
	
	
}
