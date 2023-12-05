package com.porvenir.retospring.web.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.service.implementation.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

	@InjectMocks
	private MovieController movieController;
	
	@Mock
	private MovieServiceImpl movieServiceImpl;
	
	private MockHttpServletRequest request;
	
	@BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
	
	@Test
	void whenMovieExistReturnStatusOk() throws JsonProcessingException {
		MovieEntity entity = new MovieEntity();
		when(movieServiceImpl.getMovieById(1)).thenReturn(entity);
		ResponseEntity<Map<String, Object>> response = movieController.getMovieById("1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void whenMovieIsSoLongReturnBadRequest() throws JsonProcessingException {
		ResponseEntity<Map<String, Object>> response = movieController.getMovieById("22222222222222222222222222");
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void whenMovieIsNotNumberReturnBadRequest() {
		ResponseEntity<Map<String, Object>> response = movieController.getMovieById("asd");
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void whenUpdateAMovieReturnStatusCreated() {
		MovieEntity entity = new MovieEntity();
		entity.setTitle("The Phantom Menace");
		
		MovieDto dto = new MovieDto();
		dto.setTitle("The Phantom Menaceeee");
		when(movieServiceImpl.updateMovieById(Mockito.any(MovieDto.class))).thenReturn(entity);
		ResponseEntity<Map<String, Object>> response = movieController.updateMovie(dto);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED );
	}
	
	@Test
	void whenDeleteMovieReturnStatusOk() {
		doNothing().when(movieServiceImpl).deleteMovieById(1);
		ResponseEntity<?> response = movieController.deleteFilm(1);
		assertEquals(response.getStatusCode(), HttpStatus.OK );
	}
}
