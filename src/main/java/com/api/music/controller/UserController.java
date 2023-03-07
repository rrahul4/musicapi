package com.api.music.controller;

import java.util.Set;

import javax.websocket.server.PathParam;

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
import com.api.music.model.User;
import com.api.music.service.impl.PlaylistServiceImpl;
import com.api.music.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/v1/user")
public class UserController{
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	PlaylistServiceImpl playlistService;
	
	@PostMapping("/login")
	public User login(@RequestParam String username, @RequestParam String password) {
		return userService.authenticate(username, password);
	}



	@PostMapping("/create")
	public User add(@RequestBody User obj) {
		return userService.create(obj);
	}

	@PutMapping("/update")
	public User update(User obj) {
		return userService.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(Long id) {
		userService.delete(id);
	}
	
	@PutMapping("/{username}/add/playlist/")
	public User addPlaylist(@PathVariable String username, @RequestBody Playlist playlist) {
		User user = userService.getByUserName(username);
		if(user == null) {
			System.out.println("user not found");
			return null;
		}else {
			System.out.println(user.getFname());
		}
		System.out.println("printing username and id " + user.getUsername() + ", " + user.getId());
		
		//Playlist p = playlistService.add(playlist);
		if(playlist != null) {
			user.setPlaylist(playlist);
		}
		else {
			System.out.println("playlist is null");
		}
		System.out.println("failing in update");
		return userService.updatePlaylist(user);
	}

}
