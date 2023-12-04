package com.porvenir.retospring.web.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="movie")
public class MovieEntity {

	@Id
	private Integer id;
	private Integer episodeId;
	private String title;
	private String releaseDate;
	
}
