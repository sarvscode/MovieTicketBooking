package com.example.MovieTicketBookRealProject.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.MovieTicketBookRealProject.DTO.BookingDTO;
import com.example.MovieTicketBookRealProject.Entity.Booking;
import com.example.MovieTicketBookRealProject.Entity.BookingStatus;
import com.example.MovieTicketBookRealProject.Entity.Show;
import com.example.MovieTicketBookRealProject.Entity.Users;
import com.example.MovieTicketBookRealProject.Repository.BookingRepository;
import com.example.MovieTicketBookRealProject.Repository.ShowRepository;
import com.example.MovieTicketBookRealProject.Repository.UserRepository;

@Service
public class BookingService {
	
   @Autowired
   private BookingRepository bookingRepository;
   
   @Autowired
   private ShowRepository showRepository;
   
   @Autowired
   private UserRepository userRepository;

   public Booking createBooking(BookingDTO bookingDTO) 
   {
	
	   Show show =showRepository.findById(bookingDTO.getShowId())
			      .orElseThrow(()-> new RuntimeException("Show not found"));
	   
	   if(!isSeatsAvailable(show.getId(),bookingDTO.getNumberOfSeats()))
	   {
		   
		   throw new RuntimeException("Not enough seat are avaliable");
	   }
	   
	   if(bookingDTO.getSeatNumbers().size() !=bookingDTO.getNumberOfSeats()) 
	   {
		   throw new RuntimeException("Seats Number and Number of Seatsmust be equal");
	   }
   	   
	   validateDuplicateSeats(show.getId(),bookingDTO.getSeatNumbers());
	   
	   Users user=userRepository.findById(bookingDTO.getUserId())
			      .orElseThrow(() ->new RuntimeException("user not found"));

	   Booking booking = new Booking();
	   booking.setUsers(user);
	   booking.setShow(show);
	   booking.setSeatNumbers(bookingDTO.getSeatNumbers());
	   booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
	   booking.setPrice(calculateTotalAmount(show.getPrice(),bookingDTO.getNumberOfSeats()));
	   booking.setBookingTime(LocalDateTime.now());
	   booking.setBookingStatus(BookingStatus.PENDING);
	   
	   return bookingRepository.save(booking);
   } 
   
   public List<Booking> getUserBooking(Long userId){
	   
	   return bookingRepository.findByUserId(userId);
   }
   
   public List<Booking> getShowBooking(Long showId){
	   
	   return bookingRepository.findByShowId(showId);
   }
   
   public Booking confirmBooking(Long bookingId) {
	   
	   Booking booking=bookingRepository.findById(bookingId)
			   .orElseThrow(()-> new RuntimeException("Booking is not found"));
	   
	   if(booking.getBookingStatus()!= BookingStatus.PENDING) 
	   {
		   throw new RuntimeException("Booking is not pending state");
	   }
	   
	   booking.setBookingStatus(BookingStatus.CONFIRMED);
	   return bookingRepository.save(booking);
	   
   }
   
   public Booking cancelBooking(Long bookingId) {
	   
	   Booking booking=bookingRepository.findById(bookingId)
			   .orElseThrow(()-> new RuntimeException("Booking is not found"));
	   
	   validateCancellation(booking);
	   
	   booking.setBookingStatus(BookingStatus.CANCELED);
	   return bookingRepository.save(booking) ;
   }
   
   public void validateCancellation(Booking booking) {
	   LocalDateTime showTime= booking.getShow().getShowTime();
	   LocalDateTime deadlineTime=showTime.minusHours(2);
	   
	   if(LocalDateTime.now().isAfter(deadlineTime)) 
	   {
		  throw new RuntimeException("Cannot cancel the booking"); 
	   }
	   
	   if(booking.getBookingStatus()==BookingStatus.CANCELED) 
	   {
		   throw new RuntimeException(" Booking Already cancaled"); 
	   }
   }
   
   
   public boolean isSeatsAvailable(Long showId ,Integer numberOfSeats) {
	   
	   Show show = showRepository.findById(showId)
			   .orElseThrow(()->new RuntimeException("Show not found"));
			   
	   int bookedSeats=show.getBooking().stream()
			   .filter(booking ->booking.getBookingStatus() != BookingStatus.CANCELED)
			   .mapToInt(Booking::getNumberOfSeats)
			   .sum();
	   return (show.getTheater().getTheaterCapacity()-bookedSeats) >= numberOfSeats;
   }
   
   
   public void validateDuplicateSeats(Long showId ,List<String> seatNumbers) {
	   
	   Show show =showRepository.findById(showId)
			      .orElseThrow(()-> new RuntimeException("Show not found"));
	   
	   Set<String> occupiedSeats= show.getBooking().stream()
			   .filter(b-> b.getBookingStatus()!= BookingStatus.CANCELED)
			   .flatMap(a->a.getSeatNumbers().stream())
			   .collect(Collectors.toSet());
	   
	   List<String> duplicateSeats =seatNumbers.stream()
			   .filter(occupiedSeats::contains)
			   .collect(Collectors.toList());
	   
	   if(!duplicateSeats.isEmpty()) 
	   {
		throw new RuntimeException("Seats are already booked");   
	   }
   }
  
   public Double calculateTotalAmount(Double price , Integer numberOfSeats) {
	   
	   return price* numberOfSeats;
   }

//   public Booking getBookingByStatus(BookingStatus bookingStatus) {
//	return null;
//   }
   
   
   
   
}
