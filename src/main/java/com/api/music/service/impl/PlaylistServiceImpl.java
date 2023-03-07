package com.api.music.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.music.model.Playlist;
import com.api.music.model.Song;
import com.api.music.repository.PlaylistRepository;
import com.api.music.service.BaseService;

@Service
public class PlaylistServiceImpl implements BaseService<Playlist>{
	
	@Autowired
	private PlaylistRepository repo;
	
	
	@Override
	public Set<Playlist> getAll() {
		return new HashSet<Playlist>(repo.findAll());
	}

	@Override
	public Playlist getById(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Set<Playlist> getByIds(Set<Long> ids) {
		return new HashSet<Playlist>(repo.findAllById(ids));
	}

	@Override
	public Playlist update(Playlist obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		
		
		Playlist playlist = getById(obj.getId());
		if(playlist == null) return null;
		
		playlist.setName(obj.getName() == null ? playlist.getName() : obj.getName());
		playlist.setPublic(obj.isPublic() != playlist.isPublic() ? obj.isPublic() : playlist.isPublic());
		playlist.setSongs(obj.getSongs().isEmpty() ? playlist.getSongs() : obj.getSongs());
		
		return repo.save(playlist);
	}

	@Override
	public void delete(long id) {
		Playlist play = repo.findById(id).orElse(null);
		if(play != null) {
			repo.delete(play);
		}
	}

	@Override
	public Playlist add(Playlist obj) {
		if(obj == null) return null;
		if(obj.getId() != 0) return null;
		
		obj.clearSongs();
		
		return repo.save(obj);
	}
	
	public Playlist getPlaylist(long userid, String playlistname) {
		return repo.getPlaylistByPlaylistNameAndUserId(userid, playlistname);
	}
	
	public Set<Playlist> getPlaylistsForUser(long userid){
		return repo.getPlaylistsForUser(userid);
	}
}
