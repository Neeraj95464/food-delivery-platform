package com.ynmio.PaymentServices.repository;

import com.ynmio.PaymentServices.model.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentResponseRepository extends JpaRepository<PaymentResponse,Long> {
}
