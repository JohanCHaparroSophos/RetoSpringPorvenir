package com.porvenir.retospring.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.service.implementation.MovieServiceImpl;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieServiceImpl movieServiceImpl;
	
	@GetMapping("/getFilms/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") String id){
		MovieEntity response = null;
		HttpStatus status = null;
		
		try {
			response = movieServiceImpl.getMovieById(Integer.valueOf(id));
			if (response != null) {
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(status).body(response);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			String error = e.getMessage().substring(0, 3);
			System.out.println("error sub " + error);
			if (error.equals("404")) {
				System.out.println("if 404: " );
				if(!id.matches("\\d+")) {
					System.out.println("No es numerico" );
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
				else {
					System.out.println("No existe" );
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
