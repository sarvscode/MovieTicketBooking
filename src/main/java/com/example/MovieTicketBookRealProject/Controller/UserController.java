package com.example.MovieTicketBookRealProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicketBookRealProject.DTO.LoginRequestDTO;
import com.example.MovieTicketBookRealProject.DTO.LoginResponseDTO;
import com.example.MovieTicketBookRealProject.DTO.RegisterRequestDTO;
import com.example.MovieTicketBookRealProject.Entity.Users;
import com.example.MovieTicketBookRealProject.Service.UserService;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/registernormaluser")
    public ResponseEntity<Users> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(userService.registerNormalUser(registerRequestDTO));
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }
}


