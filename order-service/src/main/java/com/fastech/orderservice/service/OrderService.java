package com.fastech.orderservice.service;

import com.fastech.orderservice.common.Payment;
import com.fastech.orderservice.controller.TransactionRequest;
import com.fastech.orderservice.controller.TransactionResponse;
import com.fastech.orderservice.entity.Order;
import com.fastech.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){

        String response="";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9091/payment/doPayment",payment,Payment.class);

        response =paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"There is a failure in payment api";

        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }


}
