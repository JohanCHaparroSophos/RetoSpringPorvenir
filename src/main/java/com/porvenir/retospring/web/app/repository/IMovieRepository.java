package com.porvenir.retospring.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porvenir.retospring.web.app.entity.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Long>{
	
	public Movie findfindById(Long idMovie);
}
