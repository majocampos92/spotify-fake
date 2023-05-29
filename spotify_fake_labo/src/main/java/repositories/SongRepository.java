package repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import models.entities.Song;

public interface SongRepository extends ListCrudRepository<Song, UUID>{
	
	Optional<Song> findById(UUID id);
	
	List<Song> findByTitle(String title);
	
	List<Song> findByDuration(Integer duration);
	
	List<Song> findByDurationOrderByAscSongs(Integer duration);

	List<Song> findByDurationOrderByDescSongs(Integer duration);
	
	List<Song> findByDurationBetweenDuration(Integer start, Integer end);

}
