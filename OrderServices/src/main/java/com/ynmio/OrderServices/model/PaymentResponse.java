package com.ynmio.OrderServices.model;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;
    private String transactionId;
    private double amount;
}
