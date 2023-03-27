package com.api.music.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.music.model.Artist;
import com.api.music.service.impl.ArtistServiceImpl;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController implements BaseController<Artist> {
	
	@Autowired
	ArtistServiceImpl artistService;
	
	
	@GetMapping("/getAll")
	public ResponseEntity<Collection<Artist>> getAll(@RequestParam int pageno) {
		return ResponseEntity.ok(artistService.getAll(pageno).toList());
	}

	@GetMapping("/getById")
	public Artist getById(@RequestParam Long id) {
		return artistService.getById(id);
	}
	
	@GetMapping("/getByName")
	public Artist getByName(@RequestParam(required = true) String name) {
		return artistService.getByName(name);
	}

	@PostMapping("/getAllByIds")
	public ResponseEntity<Collection<Artist>> getAllByIdList(@RequestBody Set<Long> ids) {
		Set<Long> artists = new HashSet<Long>();
		artists.addAll(ids);
		return ResponseEntity.ok(artistService.getByIds(artists));
	}

	@PostMapping("/create")
	public Artist add(@RequestBody Artist obj) {
		return artistService.add(obj);
	}

	@PutMapping("/update")
	public Artist update(@RequestBody Artist obj) {
		return artistService.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		artistService.delete(id);
	}
	
}
