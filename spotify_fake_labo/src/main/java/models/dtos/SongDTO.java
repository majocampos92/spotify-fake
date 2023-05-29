package models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SongDTO {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private Integer duration;
	
	@NotEmpty 
	private String playlist;

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getDuration() {
		// TODO Auto-generated method stub
		return null;
	}
}
