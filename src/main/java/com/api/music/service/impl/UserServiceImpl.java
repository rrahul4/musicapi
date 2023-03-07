package com.api.music.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.api.music.model.Playlist;
import com.api.music.model.User;
import com.api.music.repository.UserRepository;
import com.api.music.service.BaseService;

@Service
public class UserServiceImpl{

	@Autowired
	UserRepository userRepository;
	
	@AutoConfigureOrder
	PlaylistServiceImpl playlistService;

	public User getByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	public long getUserIdByUsername(String username) {
		return userRepository.findByUsername(username).getId();
	}

	public User getById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User update(User obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		System.out.println("inside user update");
		User user = getById(obj.getId());
		
		System.out.println(user.getFname());
		user.setDob(obj.getDob() == null ? user.getDob() : obj.getDob());
		user.setEmail(obj.getEmail() == null ? user.getEmail() : obj.getEmail());
		user.setFname(obj.getFname() == null ? user.getFname() : obj.getFname());
		user.setLname(obj.getLname() == null ? user.getLname() : obj.getLname());
		user.setGender(obj.getGender() == null ? user.getGender() : obj.getGender());
		user.setPassword(obj.getPassword() == null ? user.getPassword() : obj.getPassword());
		user.setPhoneno(obj.getPhoneno() == 0 ? user.getPhoneno() : obj.getPhoneno());
		
		user.setPlaylists(obj.getPlaylists());
		System.out.println("came here");
		return userRepository.save(user);
	}

	
	//needs to get user obj with updated playlist details
	public User updatePlaylist(User obj) {
		if(obj == null) return null;
		if(obj.getId() == 0) return null;
		return userRepository.save(obj);
	}
	
	public void delete(long id) {
		User user = getById(id);
		if(user != null) {
			userRepository.delete(user);
		}
	}
	
	
	public User create(User obj) {
		if(obj == null) return null;
		if(obj.getId() > 0) return null;
		System.out.println("call came out here");
		obj.clearPlaylist();
		return userRepository.save(obj);
	}
	
	public User authenticate(String username, String password) {
		//maybe some and decoding of password needs to be done.
		return userRepository.authenticate(username, password);
	}
	
}
