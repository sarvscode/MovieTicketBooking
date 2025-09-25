package com.example.MovieTicketBookRealProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieTicketBookRealProject.DTO.TheaterDTO;
import com.example.MovieTicketBookRealProject.Entity.Theater;
import com.example.MovieTicketBookRealProject.Repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	TheaterRepository theaterRepository;

	public Theater addTheater(TheaterDTO theaterDTO) {
		
		Theater theater= new Theater();
		theater.setTheaterName(theaterDTO.getTheaterName());
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
		theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
		
		return theaterRepository.save(theater);
	}

	 public List<Theater> getTheaterByLocation(String location) {
	        return theaterRepository.findByTheaterLocation(location);
	    }

	    //  Update Theater
	    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
	        Optional<Theater> optionalTheater = theaterRepository.findById(id);
	        if (optionalTheater.isPresent()) {
	            Theater theater = optionalTheater.get();
	            theater.setTheaterName(theaterDTO.getTheaterName());
	            theater.setTheaterLocation(theaterDTO.getTheaterLocation());
	            theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
	            theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
	            return theaterRepository.save(theater);
	        }
	        throw new RuntimeException("Theater not found with id " + id);
	    }

	    // Delete Theater
	    public void deleteTheater(Long id) {
	        if (theaterRepository.existsById(id)) {
	            theaterRepository.deleteById(id);
	        } else {
	            throw new RuntimeException("Theater not found with id " + id);
	        }
	    }
	
	

}
