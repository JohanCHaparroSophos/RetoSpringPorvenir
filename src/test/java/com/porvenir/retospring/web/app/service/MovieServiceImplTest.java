package com.porvenir.retospring.web.app.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.consumer.MovieConsumer;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.repository.IMovieRepository;
import com.porvenir.retospring.web.app.service.implementation.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieServiceImplTest {

	@Mock
	private IMovieRepository movieRepository;

	@Mock
	private MovieConsumer movieConsumer;

	@Autowired
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenMovieFound() throws JsonProcessingException {
		MovieDto movie = new MovieDto();
		movie.setId(1);

		MovieEntity newMovie = new MovieEntity();
		lenient().when(movieConsumer.findFilmById("1")).thenReturn(movie);

		newMovie = movieServiceImpl.getMovieById(1);
		assertNotNull(newMovie);
	}

	@Test
	void whenMovieNotFound() throws JsonProcessingException {
		MovieDto movie = new MovieDto();
		movie.setId(1);

		MovieEntity newMovie = new MovieEntity();
		lenient().when(movieConsumer.findFilmById("9")).thenReturn(null);

		newMovie = movieServiceImpl.getMovieById(9);
		assertNull(newMovie);
	}

	@Test
	void whenUpdateMovie() throws JsonProcessingException {
		MovieDto movie = new MovieDto();
		movie.setEpisodeId(1);
		MovieEntity newMovie = new MovieEntity();
		lenient().when(movieRepository.save(Mockito.any(MovieEntity.class))).thenReturn(newMovie);

		movie.setEpisodeId(2);

		newMovie = movieServiceImpl.updateMovieById(movie);
		assertNotEquals(movie.getEpisodeId(), newMovie.getEpisodeId());
	}

	@Test
	void whenDeleteMovie() {
		doNothing().when(movieRepository).deleteById(1);
		movieServiceImpl.deleteMovieById(1);
		verify(movieRepository, times(1)).deleteById(1);
	}
}
