package com.ynmio.PaymentServices.controller;


import com.ynmio.PaymentServices.model.PaymentRequest;
import com.ynmio.PaymentServices.model.PaymentResponse;
import com.ynmio.PaymentServices.repository.PaymentResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentResponseRepository paymentRepository;

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = new PaymentResponse();
        response.setStatus("SUCCESS");
        response.setTransactionId(UUID.randomUUID().toString());
        response.setAmount(paymentRequest.getAmount());
        return paymentRepository.save(response);
    }
    @GetMapping
    public List<PaymentResponse> getPayments(){
        return paymentRepository.findAll();
    }
}

