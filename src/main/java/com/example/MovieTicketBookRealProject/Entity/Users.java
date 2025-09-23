package com.example.MovieTicketBookRealProject.Entity;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table  (name ="Userss")
public class Users implements UserDetails {
	private String username;
	private String password;
	private String role;
	private String email;
	
    @ElementCollection(fetch =FetchType.EAGER)
	private Set<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	} 
}
