package com.example.MovieTicketBookRealProject.Jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.MovieTicketBookRealProject.Repository.UserRepository;
import com.example.MovieTicketBookRealProject.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader =request.getHeader("Authorization");
		final String jwtToken;
		final String username;
		
		
		if( authHeader==null ||!authHeader.startsWith("Bearer")) 
		{
			filterChain.doFilter(request, response);
			return;
			
		}
		
		//Extract the jwt token the header
		jwtToken= authHeader.substring(7);
		username=jwtService.extractUsername(jwtToken);
		
		//check if we have a username and no authentication exit yet
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) 
		{
			var userdetails= userRepository.findByusername(username)
			                               .orElseThrow(()-> new RuntimeException("user not found"));
			
			if(jwtService.isTokenValid(jwtToken , userdetails)) 
			{
				List<SimpleGrantedAuthority> authorities=userdetails.getRoles().stream()
						                                   .map(SimpleGrantedAuthority::new)
						                                   .collect(Collectors.toList());
				
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userdetails ,null,authorities);
				
				//set authentication details
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 // update the security context with authentication
				SecurityContextHolder.getContext().getAuthentication(authToken);
				
			}
			
			
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
