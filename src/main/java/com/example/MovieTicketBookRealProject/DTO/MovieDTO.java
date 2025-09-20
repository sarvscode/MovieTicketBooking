package com.example.MovieTicketBookRealProject.DTO;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;
@Entity
@Data
public class MovieDTO {

	  private String name;
	  private String describe;
	  private String genre;
	  private String language;
	  private Integer duration;
	  private LocalDate releaseDate;
}
