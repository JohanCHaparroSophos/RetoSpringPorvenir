package com.porvenir.retospring.web.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

	private Integer id;
	@JsonProperty("episode_id")
	private Integer episodeId;
	private String title;
	@JsonProperty("release_date")
	private String releaseDate;
}
