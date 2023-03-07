package com.api.music.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="Genre", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"id","id"}),
		@UniqueConstraint(columnNames = {"name", "name"})
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	String name;
	String discription;
	

	@ManyToMany(mappedBy = "genres",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(value=FetchMode.SELECT)
	private Set<Song> songs = new HashSet<Song>();
	//private Set<Song> songs = new HashSet<Song> ();
	
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	public Set<Song> getSongs() {
		return songs;
	}
	
	
	public void setSongs(Set<Song> songs) {
		for(Song s: songs) {
			this.songs.add(s);
		}
	}
	
	public void setSong(Song song) {
		this.songs.add(song);
	}
	
	public void clearSongs() {
		this.songs = new HashSet<Song>();
	}
}
