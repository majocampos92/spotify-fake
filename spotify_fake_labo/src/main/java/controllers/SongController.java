package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import models.dtos.ErrorsDTO;
import models.dtos.MessageDTO;
import models.dtos.SongDTO;
import models.entities.Song;
import services.SongService;
import utils.ErrorHandlers;

@RestController
@RequestMapping("/song")
@CrossOrigin("*")
public class SongController {

	@Autowired
	private SongService songService;
	
	@Autowired
	private ErrorHandlers errorHandler;
	
	//CREATE SONG 
	@PostMapping("/")
	public ResponseEntity<?> saveSong(@ModelAttribute @Valid SongDTO info, BindingResult validations) {
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST
					);
		}
		try {
			songService.save(info, null);
			return new ResponseEntity<>(new MessageDTO("Song Created"), HttpStatus.CREATED);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET ALL SONGS 
	@GetMapping("/songs")
	public ResponseEntity<?> findAllSongs() {
		List<Song> songs = songService.findAll();
		return new ResponseEntity<>(
				songs, HttpStatus.OK
			);
	}
	
	//GET SONG BY ID 
	@GetMapping("/{id}")
	public ResponseEntity<?> findSongById(@PathVariable(name = "id") String id) {
		Song foundSong = songService.findById(id);
		
		if(foundSong == null) {
			return new ResponseEntity<>(new MessageDTO("Song Not Found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(foundSong, HttpStatus.OK);
	}
	
	//DELETE SONG BY ID 
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteSongByID(@PathVariable(name = "id") String id, BindingResult validations) {
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST
					);
		}
		try {
			songService.deleteById(id);
			return new ResponseEntity<>(new MessageDTO("Deleted Song"), HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET ALL SONGS BY TITLE  
	@GetMapping("/songs/{title}")
	public ResponseEntity<?> findAllSongsByTitle(@PathVariable(name = "title") String title) {
		List<Song> songsByTitle = songService.findByTitle(title);
		return new ResponseEntity<>(
				songsByTitle, HttpStatus.OK
			);
	}
	
	//GET ORDER BY DURATION 
	@GetMapping("/songs/asc/{duration}")
	public  ResponseEntity<?> searchOrderByDurationAsc(@PathVariable(name = "duration") Integer duration, BindingResult validations) {
		
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST
					);
		}
		try {
			songService.findByDurationOrderByAscSongs(duration);
			return new ResponseEntity<>(new MessageDTO(""), HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/songs/desc/{duration}")
	public  ResponseEntity<?> searchOrderByDurationDesc(@PathVariable(name = "duration") Integer duration, BindingResult validations) {
				
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST
					);
		}
		try {
			songService.findByDurationOrderByDescSongs(duration);
			return new ResponseEntity<>(new MessageDTO("Success!!"), HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/songs/between/{start}/{end}")
	public  ResponseEntity<?> searchSongBetweenDuration(
			@PathVariable(name = "start") Integer start, 
			@PathVariable(name = "end") Integer end, 
			BindingResult validations
			) {
				
		if(validations.hasErrors()) {
			return new ResponseEntity<>(new ErrorsDTO(
					errorHandler.mapErrors(validations.getFieldErrors())), 
					HttpStatus.BAD_REQUEST
					);
		}
		try {
			songService.findByDurationBetweenDuration(start, end);
			return new ResponseEntity<>(new MessageDTO("Success!!"), HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/string")
	public String findString() {
		return "hola mundo";
	}
}
