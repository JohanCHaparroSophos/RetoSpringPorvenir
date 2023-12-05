package com.porvenir.retospring.web.app.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.porvenir.retospring.web.app.consumer.MovieConsumer;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.entity.MovieEntity;
import com.porvenir.retospring.web.app.repository.IMovieRepository;
import com.porvenir.retospring.web.app.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Autowired
	private MovieConsumer movieConsumer;
	
//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public MovieEntity getMovieById(Integer idMovie) throws JsonProcessingException {
		MovieEntity movie = new MovieEntity();
		MovieDto dto = movieConsumer.findFilmById(Integer.toString(idMovie));
		movie.setId(idMovie);
		movie.setEpisodeId(dto.getEpisodeId());
		movie.setTitle(dto.getTitle());
		movie.setReleaseDate(dto.getReleaseDate());
		this.registerMovie(dto);
		return movie;
	}

	@Override
	public MovieEntity registerMovie(MovieDto movie) {
////		MovieEntity entity = modelMapper.map(movie, MovieEntity.class);
		MovieEntity entity = new MovieEntity();
		entity.setId(movie.getId());
		entity.setEpisodeId(movie.getEpisodeId());
		entity.setTitle(movie.getTitle());
		entity.setReleaseDate(movie.getReleaseDate());
		return movieRepository.save(entity);
	}

	@Override
	public MovieEntity updateMovieById(MovieDto movie) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(movie.getId());
		movieEntity.setEpisodeId(movie.getEpisodeId());
		movieEntity.setTitle(movie.getTitle());
		movieEntity.setReleaseDate(movie.getReleaseDate());
//		this.registerMovie(movieEntity);
		return movieRepository.save(movieEntity);
	}

	@Override
	public void deleteMovieById(Integer idMovie) {
		movieRepository.deleteById(idMovie);
	}

}
