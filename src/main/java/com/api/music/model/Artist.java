package com.api.music.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.NoArgsConstructor;

@Entity
@Table
@Cacheable
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	private int age;
	
	@ManyToMany(mappedBy = "artists",cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SELECT)
	@JsonIgnoreProperties("artists")
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		for(Song i: songs) {
			this.songs.add(i);
		}
	}
	
	public void setSong(Song song) {
		this.songs.add(song);
	}
	
	public void clearSongs() {
		this.songs = new HashSet<Song>();
	}
	
	
}