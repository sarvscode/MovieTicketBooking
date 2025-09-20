package com.example.MovieTicketBookRealProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieTicketBookRealProject.DTO.ShowDTO;
import com.example.MovieTicketBookRealProject.Entity.Movie;
import com.example.MovieTicketBookRealProject.Entity.Show;
import com.example.MovieTicketBookRealProject.Entity.Theater;
import com.example.MovieTicketBookRealProject.Repository.MovieRepository;
import com.example.MovieTicketBookRealProject.Repository.ShowRepository;
import com.example.MovieTicketBookRealProject.Repository.TheaterRepository;

@Service
public class ShowService {
    
	    @Autowired
        private	ShowRepository showRepository;
	
	  @Autowired
	    private MovieRepository movieRepository;

	    @Autowired
	    private TheaterRepository theaterRepository;

	    // ✅ Create Show
	    public Show createShow(ShowDTO showDTO) {
	        Optional<Movie> movie = movieRepository.findById(showDTO.getMovieId());
	        Optional<Theater> theater = theaterRepository.findById(showDTO.getTheaterId());

	        if (movie.isPresent() && theater.isPresent()) {
	            Show show = new Show();
	            show.setShowTime(showDTO.getShowTime());
	            show.setPrice(showDTO.getPrice());
	            show.setMovie(movie.get());
	            show.setTheater(theater.get());
	            return showRepository.save(show);
	        }
	        throw new RuntimeException("Movie or Theater not found");
	    }

	    // ✅ Get all shows
	    public List<Show> getAllShows() {
	        return showRepository.findAll();
	    }

	    // ✅ Get shows by movie
	    public List<Show> getShowsByMovie(Long movieId) {
	        return showRepository.findByMovieId(movieId);
	    }

	    // ✅ Get shows by theater
	    public List<Show> getShowsByTheater(Long theaterId) {
	        return showRepository.findByTheaterId(theaterId);
	    }

	    // ✅ Update show
	    public Show updateShow(Long id, ShowDTO showDTO) {
	        Optional<Show> optionalShow = showRepository.findById(id);
	        if (optionalShow.isPresent()) {
	            Show show = optionalShow.get();
	            show.setShowTime(showDTO.getShowTime());
	            show.setPrice(showDTO.getPrice());

	            movieRepository.findById(showDTO.getMovieId())
	                .ifPresent(show::setMovie);
	            theaterRepository.findById(showDTO.getTheaterId())
	                .ifPresent(show::setTheater);

	            return showRepository.save(show);
	        }
	        throw new RuntimeException("Show not found with id " + id);
	    }

	    public void deleteShow(Long id) {
	        Show show = showRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Show not found with id " + id));

	        if (show.getBooking() != null && !show.getBooking().isEmpty()) {
	            throw new RuntimeException("Cannot delete show with existing bookings!");
	        }

	        showRepository.deleteById(id);
	    }
}
