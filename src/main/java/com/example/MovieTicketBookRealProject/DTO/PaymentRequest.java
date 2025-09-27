package com.example.MovieTicketBookRealProject.DTO;

import lombok.Data;

@Data
public class PaymentRequest {
    private String method;  // CARD, UPI, PAYPAL, etc.
}
