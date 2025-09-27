
package com.example.MovieTicketBookRealProject.Controller;

import com.example.MovieTicketBookRealProject.DTO.PaymentRequest;
import com.example.MovieTicketBookRealProject.Entity.Payment;
import com.example.MovieTicketBookRealProject.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay/{bookingId}")
    public ResponseEntity<Payment> makePayment(
            @PathVariable Long bookingId,
            @RequestBody PaymentRequest request) {

        return ResponseEntity.ok(paymentService.makePayment(bookingId, request.getMethod()));
    }
}
