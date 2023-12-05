package com.porvenir.retospring.web.app.proxy;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porvenir.retospring.web.app.dto.MovieDto;

@Component
public class MovieProxy {

	public static final String URL_BASE = "https://swapi.dev/api/films";

	public String findFilmById(String id) throws JsonProcessingException {
		String resp = "";
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String url = URL_BASE + "/" + id;
		ResponseEntity<MovieDto> response = restTemplate.exchange(url, HttpMethod.GET, null, MovieDto.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			response.getBody().setId(Integer.valueOf(id));
			resp = response.getBody().toString();
			String json = objectMapper.writeValueAsString(response.getBody());
			resp = json;
		}
		return resp.trim();
	};
}
