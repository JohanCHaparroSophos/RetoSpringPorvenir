package com.porvenir.retospring.web.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;

public interface IMovieService {

	public MovieEntity getMovieById(Integer idMovie) throws JsonProcessingException;
	
	public MovieEntity registerMovie(MovieDto movie);

	public MovieEntity updateMovieById(MovieDto movie);

	public void deleteMovieById(Integer idMovie);

}
