package com.fastech.paymentservice.service;

import com.fastech.paymentservice.entity.Payment;
import com.fastech.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) {
        return paymentRepository.save(payment);
    }


}
