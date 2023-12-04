package com.porvenir.retospring.web.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.entity.MovieEntity;

public interface IMovieService {

	public MovieEntity getMovieById(Integer idMovie) throws JsonProcessingException;
	
	public MovieEntity registerMovie(MovieEntity movie);

	public MovieEntity updateMovieById(MovieEntity movie);

	public boolean deleteMovieById(Integer idMovie);

}
