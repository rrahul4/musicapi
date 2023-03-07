package com.api.music.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.music.model.Genre;
import com.api.music.service.impl.GenreServiceImpl;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController{
	
	@Autowired
	GenreServiceImpl genreService;
	
	@GetMapping("/getAll")
	public Set<Genre> getAll() {
		return genreService.getAll();
	}

	@GetMapping("/getById")
	public Genre getById(@RequestParam long id) {
		return genreService.getById(id);
	}

	@PostMapping("/getAllByIds")
	public Set<Genre> getAllByIdList(@RequestBody Set<Long> ids) {
		return genreService.getByIds(ids);
	}

	@PostMapping("/create")
	public Genre add(@RequestBody Genre obj) {
		return genreService.add(obj);
	}

	@PutMapping("/update")
	public Genre update(@RequestBody Genre obj) {
		return genreService.update(obj);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam long id) {
		genreService.delete(id);
	}
	
}
