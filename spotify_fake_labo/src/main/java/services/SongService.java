package services;

import models.dtos.SongDTO;
import models.entities.Song;
import java.util.List;
import models.entities.SongXPlaylist;

public interface SongService {
	
	void save(SongDTO info, SongXPlaylist songXPlaylist) throws Exception; 
	
	void deleteById(String id) throws Exception;
	
	List<Song> findAll(); 
	
	Song findById(String id);

	List<Song> findByTitle(String title);
	
	List<Song> findByDuration(Integer duration);
	
	List<Song> findByDurationOrderByAscSongs(Integer duration);

	List<Song> findByDurationOrderByDescSongs(Integer duration);
	
	List<Song> findByDurationBetweenDuration(Integer start, Integer end);
}
