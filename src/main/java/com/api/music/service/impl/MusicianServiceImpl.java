package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.music.model.Musician;
import com.api.music.model.Song;
import com.api.music.repository.MusicianRepository;
import com.api.music.service.BaseService;

@Service
public class MusicianServiceImpl implements BaseService<Musician> {

	@Autowired
	MusicianRepository musicianRepository;
	
	
	@Override
	public Set<Musician> getAll() {
		return new HashSet<Musician>(musicianRepository.findAll());
	}

	@Override
	public Musician getById(long id) {
		return musicianRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Musician> getByIds(Set<Long> ids) {
		return new HashSet<Musician>(musicianRepository.findAllById(ids));
	}

	@Override
	public Musician update(Musician obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		
		Musician musician = getById(obj.getId());
		
		musician.setName(obj.getName() == null ? musician.getName() : obj.getName());
		
		return musicianRepository.save(musician);
	}

	@Override
	public void delete(long id) {
		if(id == 0) return;
		
		musicianRepository.delete(getById(id));
	}

	@Override
	public Musician add(Musician obj) {
		if(obj.getId() != 0) return null;
		obj.clearSongs();
		return musicianRepository.save(obj);
	}

}
