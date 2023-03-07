package com.api.music.request.model;

import java.util.HashSet;
import java.util.Set;

import com.api.music.model.Artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtistReq {
	
	private String id;
	private String name;
	private int age;
	
	//private Set<SongArtistRel> songs = new HashSet<SongArtistRel>();
	private Set<SongArtistRel> artists = new HashSet<SongArtistRel>();
	
}
