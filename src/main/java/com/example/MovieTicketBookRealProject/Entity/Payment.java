package com.example.MovieTicketBookRealProject.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
	@Data
	public class Payment {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Double amount;
	    private String paymentMethod;  // CARD, UPI, PAYPAL etc.
	    private String status;         // SUCCESS, FAILED, PENDING
	    private LocalDateTime paymentTime;

	    @OneToOne
	    @JoinColumn(name="booking_id", nullable=false)
	    private Booking booking;
	}


