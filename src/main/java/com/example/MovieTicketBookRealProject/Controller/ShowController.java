package com.example.MovieTicketBookRealProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicketBookRealProject.DTO.ShowDTO;
import com.example.MovieTicketBookRealProject.Entity.Show;
import com.example.MovieTicketBookRealProject.Service.ShowService;

@RestController
@RequestMapping("/api/show")
public class ShowController {
 
 @Autowired
 private ShowService showService;
	
 @PostMapping("/createshow")	
 public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO){
	 
	 
	 return ResponseEntity.ok(showService.createShow(showDTO));
	 
 }
 
 @GetMapping("/all")
 public ResponseEntity<List<Show>> getAllShows() {
     return ResponseEntity.ok(showService.getAllShows());
 }

 // ✅ Get shows by Movie ID
 @GetMapping("/movie/{movieId}")
 public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long movieId) {
     return ResponseEntity.ok(showService.getShowsByMovie(movieId));
 }

 // ✅ Get shows by Theater ID
 @GetMapping("/theater/{theaterId}")
 public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long theaterId) {
     return ResponseEntity.ok(showService.getShowsByTheater(theaterId));
 }

 // ✅ Update Show
 @PutMapping("/update/{id}")
 public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
     return ResponseEntity.ok(showService.updateShow(id, showDTO));
 }

 // ✅ Delete Show
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
     showService.deleteShow(id);
     return ResponseEntity.ok().build();
 }
 
}
