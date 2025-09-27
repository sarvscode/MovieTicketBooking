package com.example.MovieTicketBookRealProject.Repository;

import com.example.MovieTicketBookRealProject.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
