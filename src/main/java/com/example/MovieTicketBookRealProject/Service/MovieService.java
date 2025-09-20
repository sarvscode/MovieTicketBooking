package com.example.MovieTicketBookRealProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieTicketBookRealProject.DTO.MovieDTO;
import com.example.MovieTicketBookRealProject.Entity.Movie;
import com.example.MovieTicketBookRealProject.Repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;

	public Movie addMovies(MovieDTO movieDTO) {
		
		Movie movie = new Movie();
		movie.setName(movieDTO.getName());
		movie.setDescribe(movieDTO.getDescribe());
		movie.setGenre(movieDTO.getGenre());
		movie.setReleaseDate(movieDTO.getReleaseDate());
		movie.setDuration(movieDTO.getDuration());
		movie.setLanguage(movieDTO.getLanguage());
		
		return movieRepository.save(movie);
		
		
	}

	public List< Movie> getAllMovie() {
		
		return movieRepository.findAll();
	}
	
    public List< Movie> getMovieByGenre(String genre) {
		
	Optional<List<Movie>> listOfMovieBox= movieRepository.findByGenre(genre);
	
	if(listOfMovieBox.isPresent()) {
		return listOfMovieBox.get();
	}
	else throw new RuntimeException("no movies found for genre"+ genre);
		
	
	}
	
     public List< Movie> getMovieByLanguage(String language) {
		
    	Optional<List<Movie>> listOfMovieBox= movieRepository.findByLanguage(language);
    	
    	if(listOfMovieBox.isPresent()) {
    		return listOfMovieBox.get();
    	}
    	else throw new RuntimeException("no movies found for Language"+ language);
    		
    	}

	 public Movie getMovieByTitle(String title) {
		 Optional<Movie> movieBox= movieRepository.findByName(title);
	    	
	    	if(movieBox.isPresent()) {
	    		return movieBox.get();
	    	}
	    	else throw new RuntimeException("no movies found for Language"+ title);
	    		
	 }

	 public Movie updateMovie(Long id,MovieDTO movieDTO) {
		
		Movie movie= movieRepository.findById(id).orElseThrow(()-> new RuntimeException("no Movie Found for the id") );
		 
		movie.setName(movieDTO.getName());
		movie.setDescribe(movieDTO.getDescribe());
		movie.setGenre(movieDTO.getGenre());
		movie.setReleaseDate(movieDTO.getReleaseDate());
		movie.setDuration(movieDTO.getDuration());
		movie.setLanguage(movieDTO.getLanguage());
		
		return movieRepository.save(movie);
		
	 }

	 public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
		
	 }
    
    
	

}
