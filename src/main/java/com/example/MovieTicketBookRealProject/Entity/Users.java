package com.example.MovieTicketBookRealProject.Entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table  (name ="Userss")
public class Users implements UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // primary key
    private Long id;
	private String username;
	private String password;
	private String role;
	private String email;
	
    @ElementCollection(fetch =FetchType.EAGER)
	private Set<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles .stream()
				      .map(SimpleGrantedAuthority::new)
				      .collect(Collectors.toList());
	} 
}
