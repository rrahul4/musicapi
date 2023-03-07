package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.music.model.Album;
import com.api.music.model.Song;
import com.api.music.repository.AlbumRepository;
import com.api.music.service.BaseService;

@Service
public class AlbumServiceImpl implements BaseService<Album>{
	
	@Autowired
	AlbumRepository repo;
	
	@Autowired
	SongServiceImpl songService;
	
	public Album getByName(String name) {
		System.out.println(name);
		return repo.findByName(name);
	}
	
	@Override
	public Set<Album> getAll() {
		return new HashSet<Album>(repo.findAll());
	}

	@Override
	public Album getById(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Set<Album> getByIds(Set<Long> ids) {
		return new HashSet<Album>(repo.findAllById(ids));
	}

	@Override
	public Album update(Album obj) {
		if(obj.getId() == 0) {
			return null;
		}
		
		Album album = getById(obj.getId());
		if(album == null) {
			return null;
		}
		
		if(getByName(obj.getName()) != null) {
			return null;
		}
		
		album.setName(obj.getName());
		
		for(Song s : obj.getSongs()) {
			s = songService.getById(s.getId());
			if(s != null) {
				album.setSong(s);
			}
		}
		
		return repo.save(album);
	}

	@Override
	public void delete(long id) {
		Album entity = repo.findById(id).orElse(null);
		if(entity != null) {
			repo.delete(entity);
		}
	}

	@Override
	public Album add(Album obj) {
		if(obj.getId() != 0) {
			return null;
		}
		
		obj.clearSongs();
		return repo.save(obj);
	}
	
	public Album mapSongs(long albumId, Set<Long> songids) {
		Album album = getById(albumId);
		
		if(album == null) return null;
		
		for(long s: songids) {
			album.setSong(songService.getById(s));
		}
		
		return repo.save(album);
	}

}
