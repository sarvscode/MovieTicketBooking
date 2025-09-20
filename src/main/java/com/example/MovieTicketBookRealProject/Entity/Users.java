package com.example.MovieTicketBookRealProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table  (name ="Userss")
public class Users {
	private String name;
	private String password;
	private String role;
	private String email;
	

}
