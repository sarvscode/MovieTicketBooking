package com.example.MovieTicketBookRealProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieTicketBookRealProject.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
