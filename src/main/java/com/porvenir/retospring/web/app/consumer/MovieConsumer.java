package com.porvenir.retospring.web.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porvenir.retospring.web.app.dto.MovieDto;
import com.porvenir.retospring.web.app.proxy.MovieProxy;

@Component
public class MovieConsumer {

	@Autowired
	MovieProxy movieProxy;

	public MovieDto findFilmById(String id) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		MovieDto resp = null;
		String sBody = "";
		sBody = movieProxy.findFilmById(id);
		resp = objectMapper.readValue(sBody, MovieDto.class);
		return resp;
	}
}
