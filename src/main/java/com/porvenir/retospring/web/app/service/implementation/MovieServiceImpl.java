package com.porvenir.retospring.web.app.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.consumer.MovieConsumer;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.repository.IMovieRepository;
import com.porvenir.retospring.web.app.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

	@Autowired
	private IMovieRepository movieRepository;
	
	@Autowired
	private MovieConsumer movieConsumer;
	
	@Override
	public MovieEntity getMovieById(Integer idMovie) throws JsonProcessingException {
		MovieEntity movie = new MovieEntity();
		MovieDto dto = movieConsumer.findFilmById(Integer.toString(idMovie));
		movie.setId(idMovie);
		movie.setEpisodeId(dto.getEpisodeId());
		movie.setTitle(dto.getTitle());
		movie.setReleaseDate(dto.getReleaseDate());
		return movie;
	}

	@Override
	public MovieEntity registerMovie(MovieEntity movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieEntity updateMovieById(MovieEntity movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteMovieById(Integer idMovie) {
		// TODO Auto-generated method stub
		return false;
	}

	


	

	
}
