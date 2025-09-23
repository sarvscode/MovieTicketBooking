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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicketBookRealProject.DTO.BookingDTO;
import com.example.MovieTicketBookRealProject.Entity.Booking;
import com.example.MovieTicketBookRealProject.Entity.BookingStatus;
import com.example.MovieTicketBookRealProject.Service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
	@Autowired
	private BookingService bookingService;
	
		@PostMapping("/createbooking")
		public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
		
		
		return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
	}
	
		@GetMapping("/getuserbooking/{id}")
    	public ResponseEntity<List<Booking>> getUserBooking(@PathVariable  Long id){
		
		
		return ResponseEntity.ok(bookingService.getUserBooking(id));
	}
	 
		@GetMapping("/getshowbooking/{id}")
    	public ResponseEntity<List<Booking>> getShowBooking(@PathVariable  Long id){
		
		
		return ResponseEntity.ok(bookingService.getShowBooking(id));
	}
		
		
		@PutMapping("/confirmbooking/{id}")
    	public ResponseEntity<Booking> confirmBooking(@PathVariable  Long id){
		
		
		return ResponseEntity.ok(bookingService.confirmBooking(id));
	}
		
		
		@PutMapping("/cancel/{id}")
    	public ResponseEntity<Booking> cancelBooking(@PathVariable  Long id){
		
		
		return ResponseEntity.ok(bookingService.cancelBooking(id));
	}
		
//		@GetMapping("/getbookingbystatus/{bookingStatus}")
//    	public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable  BookingStatus bookingStatus){
//		
//		
//		return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
//	}
		
		
		
		
		
		
		
}
