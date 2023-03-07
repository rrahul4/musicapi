package com.api.music.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name="playlist")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name="user_id", nullable = false)
	private long userid;
	
	private boolean isPublic = true;
	
	private String createdon = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
	
	
	@ManyToMany(targetEntity = Song.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "song_id")
			)
	@Fetch(value=FetchMode.SELECT)
	@JsonIgnoreProperties("playlists")
	private Set<Song> songs = new HashSet<Song>();
	//Set<Song> songs = new HashSet<>();

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

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return id + ", " + name + ", " + isPublic + ", " + userid;
	}
	
	
}
