package com.api.music.request.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongReq {
	String id;
	String name;
	float duration;
	int likes;
	
	private Set<SongArtistRel> artists = new HashSet<SongArtistRel> ();
}
