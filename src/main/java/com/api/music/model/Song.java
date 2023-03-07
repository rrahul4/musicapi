package com.api.music.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="Song", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"})
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(exclude = {"songs"})
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private int year;
	private String date;
	private float duration;
	private int likes;
	private int played;   //no of time played will help in the ranking of the songs against other songs.
	private String imgurl;
	private String downloadlink128kbps;
	private String downloadlink320kbps;
	


	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="album_id", referencedColumnName = "id")    
	@Fetch(value = FetchMode.SELECT)
	@JsonIgnoreProperties("songs")
	private Album album;
	
	//many to many relationship with musicians
	@ManyToMany(targetEntity = Musician.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			joinColumns = @JoinColumn(name="song_id"),
			inverseJoinColumns = @JoinColumn(name="musician_id")
			)
	@Fetch(value = FetchMode.SELECT)
	@JsonIgnoreProperties("songs")
	private Set<Musician> musicBy = new HashSet<Musician>();
	
	
	//many to many relationship with artists
	@ManyToMany(targetEntity = Artist.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			joinColumns = @JoinColumn(name="song_id"),
			inverseJoinColumns = @JoinColumn(name="artist_id")
			)
	@Fetch(value = FetchMode.SELECT)
	@JsonIgnoreProperties("songs")
	private Set<Artist> artists = new HashSet<Artist>();
	
	
	//many to many relationship with genre
	@ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			joinColumns = @JoinColumn(name="song_id"),
			inverseJoinColumns = @JoinColumn(name="genre_id")
			)
	@Fetch(value = FetchMode.SELECT)
	@JsonIgnoreProperties("songs")
	private Set<Genre> genres = new HashSet<Genre>();
		
	
	//many to many with playlists
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany(mappedBy = "songs",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("songs")
	private Set<Playlist> playlists = new HashSet<Playlist>();
	
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


	public float getDuration() {
		return duration;
	}


	public void setDuration(float duration) {
		this.duration = duration;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public Set<Artist> getArtists() {
		return artists;
	}


	public void setArtists(Set<Artist> artists) {
		for(Artist i : artists) {
			this.artists.add(i);
		}
	}
	
	public void setArtist(Artist artist) {
		this.artists.add(artist);
	}
	
	public void clearArtists() {
		this.artists = new HashSet<Artist>();
	}

	public Set<Genre> getGenres() {
		return genres;
	}


	public void setGenres(Set<Genre> genres) {
		for(Genre g : genres) {
			this.genres.add(g);
		}
	}
	
	public void setGenre(Genre genres) {
		this.genres.add(genres);
	}
	
	public void clearGenre() {
		this.genres = new HashSet<Genre>();
	}
	
	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		for(Playlist p : playlists) {
			this.playlists.add(p);
		}
	}
	
	public void setPlaylist(Playlist playlists) {
		this.playlists.add(playlists);
	}
	
	public void clearPlaylist() {
		this.playlists = new HashSet<Playlist>();
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getPlayed() {
		return played;
	}


	public void setPlayed(int played) {
		this.played = played;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public Set<Musician> getMusicBy() {
		return musicBy;
	}


	public void setMusicBy(Set<Musician> musicBy) {
		for(Musician m : musicBy) {
			this.musicBy.add(m);
		}
	}
	
	public void setMusician(Musician musicBy) {
		this.musicBy.add(musicBy);
	}
	
	public void clearMusician() {
		this.musicBy = new HashSet<Musician>();
	}
	
	public void clearAllRelation() {
		this.artists = new HashSet<Artist>();
		this.playlists = new HashSet<Playlist>();
		this.genres = new HashSet<Genre>();
		this.musicBy = new HashSet<Musician>();
	}
	
	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	public String getDownloadlink128kbps() {
		return downloadlink128kbps;
	}


	public void setDownloadlink128kbps(String downloadlink128kbps) {
		this.downloadlink128kbps = downloadlink128kbps;
	}


	public String getDownloadlink320kbps() {
		return downloadlink320kbps;
	}


	public void setDownloadlink320kbps(String downloadlink320kbps) {
		this.downloadlink320kbps = downloadlink320kbps;
	}
	
}