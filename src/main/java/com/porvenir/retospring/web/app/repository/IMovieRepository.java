package com.porvenir.retospring.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porvenir.retospring.web.app.entity.MovieEntity;

public interface IMovieRepository extends JpaRepository<MovieEntity, Integer>{
	
	
}
