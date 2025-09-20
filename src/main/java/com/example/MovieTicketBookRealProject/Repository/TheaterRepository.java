package com.example.MovieTicketBookRealProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieTicketBookRealProject.DTO.TheaterDTO;
import com.example.MovieTicketBookRealProject.Entity.Theater;

@Repository 
public interface TheaterRepository extends JpaRepository<Theater, Long> {

	List<Theater> findByTheaterLocation(String location);


}
