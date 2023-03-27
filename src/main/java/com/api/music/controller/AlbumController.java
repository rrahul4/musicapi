package com.api.music.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.music.model.Album;
import com.api.music.service.impl.AlbumServiceImpl;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController implements BaseController<Album> {
	
	@Autowired
	AlbumServiceImpl service;
	
	@GetMapping("/getAll")
	public ResponseEntity<Collection<Album>> getAll(@RequestParam int pageno) {
		Page<Album> page = service.getAll(pageno);
		return ResponseEntity.ok(page.toList());
	}

	@GetMapping("/getById")
	public Album getById(@RequestParam Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/getByName")
	public List<Album> getByName(@RequestParam String name) {
		return service.getByName(name);
	}

	@GetMapping("/getAllById")
	public ResponseEntity<Collection<Album>> getAllByIdList(@RequestParam Set<Long> ids) {
		Set<Long> abms = new HashSet<Long>();
		for(long i : ids) {
			abms.add(i);
		}
		return ResponseEntity.ok(service.getByIds(abms));
	}
	
	
	@PutMapping("/addSongs/{albumid}")
	public Album addSongs(@PathVariable long albumid, @RequestBody Set<Long> songids) {
		return service.mapSongs(albumid, songids);
	}
	
	

	@PostMapping("/create")
	public Album add(@RequestBody Album obj) {
		return service.add(obj);
	}

	
	@PutMapping("/update")
	public Album update(@RequestBody Album obj) {
		return service.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		service.delete(id);
	}
}
