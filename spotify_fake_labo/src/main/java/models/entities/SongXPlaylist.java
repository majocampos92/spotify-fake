package models.entities;


import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songxplaylist")
public class SongXPlaylist {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_code", nullable = true)
	private Song songCode;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_code", nullable = true)
	private Playlist playlistCode;
	
	@Column(name = "date_added")
	private Date dateAdded;

	public SongXPlaylist(Song songCode, Playlist playlistCode, Date dateAdded) {
		super();
		this.songCode = songCode;
		this.playlistCode = playlistCode;
		this.dateAdded = dateAdded;
	}
	
}
