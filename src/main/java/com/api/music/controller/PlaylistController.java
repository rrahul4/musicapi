package com.api.music.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.music.model.Playlist;
import com.api.music.model.Song;
import com.api.music.service.impl.PlaylistServiceImpl;
import com.api.music.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/v1/playlist")
public class PlaylistController implements BaseController<Playlist> {
	
	@Autowired
	private PlaylistServiceImpl service;
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/getAll")
	public Set<Playlist> getAll() {
		return service.getAll();
	}

	@GetMapping("/getById")
	public Playlist getById(@RequestParam Long id) {
		return service.getById(id);
	}

	@PostMapping("/getAllByIds")
	public Set<Playlist> getAllByIdList(@RequestBody Set<Long> ids) {
		return service.getByIds(ids);
	}

	@PostMapping("/create")
	public Playlist add(@RequestBody Playlist obj) {
		System.out.println(obj.toString());
		return service.add(obj);
	}

	@PutMapping("/update")
	public Playlist update(@RequestParam Playlist obj) {
		return service.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		service.delete(id);
	}
	
	
	//types could be like homerow, featured, special, general
	@GetMapping("/getPlaylist")
	public Playlist getTrendingSongs(@RequestParam String username, @RequestParam String playlist){
		long userId = userService.getUserIdByUsername(username);
		return service.getPlaylist(userId, playlist);
	}
	
	//types could be like homerow, featured, special, general
	@GetMapping("/searchPlaylist/{userid}")
	public Playlist getTrendingSongs(@PathVariable long userid, @RequestBody String playlistname){
		//long userId = userService.getUserIdByUsername(username);
		return service.getPlaylist(userid, playlistname);
	}
	
	@GetMapping("/getPlaylistForUser/{userid}")
	public Set<Playlist> getPlaylistsForUser(@PathVariable long userid){
		return service.getPlaylistsForUser(userid);
	}
	
	@GetMapping("/getUserPlaylist")
	public Playlist getUserPlaylist(@RequestParam long userid, @RequestParam String playlist){
		return service.getPlaylist(userid, playlist);
	}
	

}
