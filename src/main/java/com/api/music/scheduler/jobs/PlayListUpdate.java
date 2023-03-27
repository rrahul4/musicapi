package com.api.music.scheduler.jobs;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.api.music.model.Playlist;
import com.api.music.model.Song;
import com.api.music.service.impl.PlaylistServiceImpl;
import com.api.music.service.impl.SongServiceImpl;
import com.api.music.service.impl.UserServiceImpl;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;


@Service
public class PlayListUpdate {
	
	@Autowired
	SongServiceImpl songService;
	
	@Autowired
	PlaylistServiceImpl playlistService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Scheduled(cron = "${interval-in-cron}")
	public void updateTrendingSongsPlaylist() {
		
		System.out.println("running update trending playlist job");
		
		PageRequest request = PageRequest.of(1, 20, Sort.by(Sort.Direction.DESC, "year", "played"));
		
		Set<Song> trendingSongs = songService.getAll(request).toSet();
		
		for(Song s : trendingSongs) {
			System.out.println(s.toString());
		}
		
		long user_id = userServiceImpl.getUserIdByUsername("dhunnn");
		
		Playlist p = playlistService.getPlaylist(user_id, "Trending_Songs");
		/*
		for(Song s: trendingSongs) {
			System.out.println(s.getId());
		}*/
		
		System.out.println(trendingSongs.size());
		
		p.clearSongs();
		p.setSongs(trendingSongs);
		
		playlistService.update(p);
	}
	
	/*
	@Scheduled(cron = "${interval-in-cron}")
	public void updateLatestSongsPlaylist() {
		
		System.out.println("running update latest playlist job");
		Set<Song> latestSongs = songService.getlatestSongs(20).toSet();
		
		long user_id = userServiceImpl.getUserIdByUsername("dhunnn");
		
		Playlist p = playlistService.getPlaylist(user_id, "Latest_Songs");
		
		System.out.println(latestSongs.size());
		
		if(!latestSongs.equals(p.getSongs())) {
			latestSongs.addAll(p.getSongs());
			p.setSongs(latestSongs);
		
			playlistService.update(p);
		}
	}*/
	
	@Scheduled(cron = "${interval-in-cron}")
	public void updateNewSongsPlaylist() {
		
		System.out.println("running update latest playlist job");
		int year = LocalDateTime.now().getYear();
		
		
		PageRequest request = PageRequest.of(1, 20, Sort.by(Sort.Direction.DESC, "year"));
	
		Set<Song> newsongs = songService.getAll(request).toSet();
		
		for(Song s : newsongs) {
			System.out.println(s.toString());
		}
		
		long user_id = userServiceImpl.getUserIdByUsername("dhunnn");
		
		Playlist p = playlistService.getPlaylist(user_id, "New_Songs");
		
		System.out.println(newsongs.size());
		
		if(!newsongs.equals(p.getSongs())){
			p.setSongs(newsongs);
			playlistService.update(p);
		}
	}
	
}
