package com.example.MovieTicketBookRealProject.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequestDTO {
	 @JsonProperty("email")
	private String username;
	private String password;
}
