package com.example.MovieTicketBookRealProject.DTO;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class LoginRequestDTO {

	private String username;
	private String password;
}
