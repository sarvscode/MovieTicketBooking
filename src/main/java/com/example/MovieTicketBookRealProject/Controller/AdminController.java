package com.example.MovieTicketBookRealProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicketBookRealProject.DTO.RegisterRequestDTO;
import com.example.MovieTicketBookRealProject.Entity.Users;
import com.example.MovieTicketBookRealProject.Service.UserService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
private	UserService userService;

	
	@PostMapping("/registeradmin")
	public ResponseEntity<Users> registerAdmin(@RequestBody RegisterRequestDTO registerRequestDTO){
		
		return ResponseEntity.ok(userService.registerNormalUser(registerRequestDTO));
	}
}
