package com.example.MovieTicketBookRealProject.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MovieTicketBookRealProject.DTO.LoginRequestDTO;
import com.example.MovieTicketBookRealProject.DTO.LoginResponseDTO;
import com.example.MovieTicketBookRealProject.DTO.RegisterRequestDTO;
import com.example.MovieTicketBookRealProject.Entity.Users;
import com.example.MovieTicketBookRealProject.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public Users registerNormalUser(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByusername(registerRequestDTO.getUsername()).isPresent()) 
		{
		throw new RuntimeException("User already register");
		}
		
		Set<String> roles=new HashSet<String>();
		roles.add("ROLE_USER");
		
		Users user=new Users();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		
		return userRepository.save(user) ;
	}
	
	

	public Users registerAdmin(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByusername(registerRequestDTO.getUsername()).isPresent()) 
		{
		throw new RuntimeException("User already register");
		}
		
		Set<String> roles=new HashSet<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		
		Users user=new Users();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		
		return userRepository.save(user) ;
	}
	
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		
		Users user=userRepository.findByusername(loginRequestDTO.getUsername())
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
		
		String token=jwtService.generateToken(user);
		
		return LoginResponseDTO.builder()
				               .jwtToken(token)
				               .username(user.getUsername())
				               .roles(user.getRoles())
				               .build();
		
		
	}

}
