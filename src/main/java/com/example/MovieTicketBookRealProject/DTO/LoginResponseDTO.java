package com.example.MovieTicketBookRealProject.DTO;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

	private String jwtToken;
	private String username;
	private  Set<String> roles;
	
}
