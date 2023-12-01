package com.porvenir.retospring.web.app.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porvenir.retospring.web.app.entity.Movie;
import com.porvenir.retospring.web.app.repository.IMovieRepository;
import com.porvenir.retospring.web.app.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

	@Autowired
	private IMovieRepository movieRepository;
	
	@Override
	public Movie getMovieById(Long idMovie) {
		
		return null;
	}

	@Override
	public Movie updateMovieById(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteMovieById(Long idMovie) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
