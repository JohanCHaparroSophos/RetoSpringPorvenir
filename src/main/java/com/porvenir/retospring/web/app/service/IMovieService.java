package com.porvenir.retospring.web.app.service;

import com.porvenir.retospring.web.app.entity.Movie;

public interface IMovieService {

	public Movie getMovieById(Long idMovie);

	public Movie updateMovieById(Movie movie);

	public boolean deleteMovieById(Long idMovie);

}
