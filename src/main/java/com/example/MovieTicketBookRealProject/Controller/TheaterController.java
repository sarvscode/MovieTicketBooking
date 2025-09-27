package com.example.MovieTicketBookRealProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicketBookRealProject.DTO.TheaterDTO;
import com.example.MovieTicketBookRealProject.Entity.Theater;
import com.example.MovieTicketBookRealProject.Service.TheaterService;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    
	@Autowired
	TheaterService theaterService;
	
	@PostMapping("/addtheater")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO){
		
		
		return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
	}
	
	@GetMapping("/gettheaterbylocation")
	public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location){
		
		return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
	}
	
	@PutMapping("/updatetheater")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theater> updateTheater(@PathVariable Long id,@RequestParam TheaterDTO theaterDTO){
		
		return ResponseEntity.ok(theaterService.updateTheater(id,theaterDTO));
	}
	
	
	@DeleteMapping("/deletethreater/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteTheater(@PathVariable Long id){
		theaterService.deleteTheater(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	
	
	
	
	
	
}
