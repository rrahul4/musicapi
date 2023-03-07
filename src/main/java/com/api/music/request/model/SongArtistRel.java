package com.api.music.request.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongArtistRel {

	private String songId;
	
	private String artistId;
	
}
