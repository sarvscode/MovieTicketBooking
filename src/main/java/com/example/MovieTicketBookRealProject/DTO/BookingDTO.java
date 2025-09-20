package com.example.MovieTicketBookRealProject.DTO;


import java.time.LocalDateTime;
import java.util.List;

import com.example.MovieTicketBookRealProject.Entity.BookingStatus;

import lombok.Data;
@Data
public class BookingDTO {

	    private Integer numberOfSeats;
	    private LocalDateTime bookingTime;
	    private Double price;
	    private BookingStatus bookingStatus;
	    
	    private List<String> seatNumbers;
	    private Long userId;
	    private Long showId;
	    
	    
	    
	    
	    
	    
	    
}
