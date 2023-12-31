package com.api.music.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.NoArgsConstructor;


@Entity
@Cacheable
@Table(name="Album", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"id","id"}),
		@UniqueConstraint(columnNames = {"name", "name"})
})
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	
	private String imgurl;

	@OneToMany(targetEntity=Song.class, mappedBy="album",cascade= {CascadeType.MERGE} , fetch = FetchType.LAZY) 
	private Set<Song> songs = new HashSet<Song>();
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Song> getSongs() {
		return songs;
	}
	
	public void setSongs(Set<Song> songs) {
		for(Song s : songs) {
			this.songs.add(s);
		}
	}
	
	public void setSong(Song song) {
		this.songs.add(song);
	}
	
	public void clearSongs() {
		this.songs = new HashSet<Song>();
	}
	
	public String toString() {
		return "id = " + getId() + ", name = " + getName();
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
}
