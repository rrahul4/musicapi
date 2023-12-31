package com.api.music.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
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

import com.api.music.model.Song;
import com.api.music.service.impl.SongServiceImpl;

@RestController
@RequestMapping("/api/v1/song")
public class SongController implements BaseController<Song> {

	@Autowired
	SongServiceImpl songService;
	
	@GetMapping("/getAll")
	public ResponseEntity<Collection<Song>> getAll(@RequestParam int pageno) {
		return ResponseEntity.ok(songService.getAll(pageno).toList());
	}
	

	@GetMapping("/getById")
	public Song getById(@RequestParam Long id) {
		return songService.getById(id);
	}
	
	@GetMapping("/getByName")
	public Song getByName(@RequestParam String name) {
		return songService.getByName(name);
	}
	
	@GetMapping("/getNewSongs")
	public ResponseEntity<Collection<Song>> getNewSongs(@RequestParam int pageno){
		int year = LocalDateTime.now().getYear();
		return ResponseEntity.ok(songService.getNewSongs(year, pageno).toList());
	}
	
	@GetMapping("/getOldSongs")
	public ResponseEntity<Collection<Song>> getOldSongs(@RequestParam int pageno){
		int year = LocalDateTime.now().getYear();
		return ResponseEntity.ok(songService.getOldTrendingSongs(pageno).toList());
	}

	@PostMapping("/getAllByIds")
	public ResponseEntity<Collection<Song>> getAllByIdList(@RequestBody Set<Long> ids) {
		Set<Long> songids = new HashSet<Long>();
		songids.addAll(ids);
		return ResponseEntity.ok(songService.getByIds(songids));
	}

	@PostMapping("/create")
	public Song add(@RequestBody Song obj) {
		return songService.add(obj);
	}

	@PutMapping("/update")
	public Song update(@RequestBody Song obj) {
		return songService.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		songService.delete(id);	
	}	
}
