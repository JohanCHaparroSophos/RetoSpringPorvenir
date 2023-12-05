package com.porvenir.retospring.web.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.service.implementation.MovieServiceImpl;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieServiceImpl movieServiceImpl;
	
	@GetMapping("/getFilms/{id}")
	public ResponseEntity<Map<String, Object>> getMovieById(@PathVariable("id") String id){
		Map<String, Object> responseMap = new HashMap<>();
		MovieEntity response = null;
		HttpStatus status = null;
		
		try {
			response = movieServiceImpl.getMovieById(Integer.valueOf(id));
			responseMap.put("movie", response);
			if (response != null) {
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(status).body(responseMap);
		} catch (Exception e) {
			String error = e.getMessage().substring(0, 3);
			if (error.equals("404")) {
				if(!id.matches("\\d+")) {
					responseMap.put("message", "Error en el sistema");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
				}
				else {
					responseMap.put("movie", response);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMap);
				}
			}
			responseMap.put("message", "Error en el sistema");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
		}
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<Map<String, Object>> updateMovie(@RequestBody MovieDto movie)  {
		Map<String, Object> response = new HashMap<>();
		MovieEntity movieEntity = null;
		HttpStatus status = null;
		String message = "";
		try {
			movieEntity = movieServiceImpl.updateMovieById(movie);
			if (movieEntity != null) {
				status = HttpStatus.CREATED;
				message = "Registro actualizado";
			} else {
				status = HttpStatus.BAD_REQUEST;
				message = "Error al actualizar el registro";
			}
			response.put("body", movieEntity);
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			message = e.getMessage();
			response.put("body", null);
			e.printStackTrace();
		}
		response.put("status", status);
		response.put("message", message);
		
		return ResponseEntity.status(status).body(response);
	}
	
	@DeleteMapping("/deleteMovie/{idMovie}")
	public ResponseEntity<?> deleteFilm(@PathVariable("idMovie") Integer idMovie){
		boolean response = true;
		HttpStatus status = null;
		try {
			movieServiceImpl.deleteMovieById(idMovie);
			status = HttpStatus.OK;
			return ResponseEntity.status(status).body(response);
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			response = false;
			return ResponseEntity.status(status).body(response);
		}
	}
	
}
