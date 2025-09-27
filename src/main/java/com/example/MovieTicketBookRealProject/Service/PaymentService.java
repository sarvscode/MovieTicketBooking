
package com.example.MovieTicketBookRealProject.Service;

import com.example.MovieTicketBookRealProject.Entity.Booking;
import com.example.MovieTicketBookRealProject.Entity.BookingStatus;
import com.example.MovieTicketBookRealProject.Entity.Payment;
import com.example.MovieTicketBookRealProject.Repository.BookingRepository;
import com.example.MovieTicketBookRealProject.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(Long bookingId, String method) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking is not pending");
        }

        Payment payment = new Payment();
        payment.setAmount(booking.getPrice());
        payment.setPaymentMethod(method);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("SUCCESS");
        payment.setBooking(booking);

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }
}
