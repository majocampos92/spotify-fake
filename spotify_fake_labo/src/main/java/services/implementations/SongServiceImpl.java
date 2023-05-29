package services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.dtos.SongDTO;
import models.entities.Song;
import models.entities.SongXPlaylist;
import repositories.SongRepository;
import services.SongService;

import jakarta.transaction.Transactional;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository songRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SongDTO info, SongXPlaylist songXPlaylist) throws Exception {
		Song song = new Song(
				info.getTitle(),
				info.getDuration(),
				songXPlaylist
				);
		songRepository.save(song);
	}

	@Override
	public void deleteById(String id) throws Exception {
		UUID code = UUID.fromString(id);
		songRepository.deleteById(code);
	}

	@Override
	public List<Song> findAll() {
		return songRepository.findAll();
	}

	@Override
	public Song findById(String id) {
		try {
			UUID code = UUID.fromString(id);
			return songRepository.findById(code)
					.orElse(null);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public List<Song> findByTitle(String title) {
		try {
			return songRepository.findByTitle(title);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public List<Song> findByDuration(Integer duration) {
		try {
			return songRepository.findByDuration(duration);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public List<Song> findByDurationOrderByAscSongs(Integer duration) {
		try {
			return songRepository.findByDurationOrderByAscSongs(duration);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public List<Song> findByDurationOrderByDescSongs(Integer duration) {
		try {
			return songRepository.findByDurationOrderByDescSongs(duration);
		} catch (Exception error) {
			return null;
		}
	}

	@Override
	public List<Song> findByDurationBetweenDuration(Integer start, Integer end) {
		try {
			return songRepository.findByDurationBetweenDuration(start, end);
		} catch (Exception error) {
			return null;
		}
	}
}
