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
    private RestTemplate template;



    public TransactionResponse saveOrder(TransactionRequest request){

        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        Payment paymentResponse = template.postForObject("http://localhost:9091/payment/doPayment",payment,Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is a failure in payment api";

        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response) ;
    }







//
//
//    public TransactionResponse saveOrder(TransactionRequest request){
//
//        String response = "";
//        Order order = request.getOrder();
//        Payment payment = request.getPayment();
////
////        if (order != null) {
////            payment.setOrderId(order.getId());
////            payment.setAmount(order.getPrice());
////        } else {
////            // Handle the case when order is null
////            // For example, you could throw an exception or return an error response
////            // depending on your application's requirements.
////            // ...
////        }
////
//////        Payment paymentResponse = template.postForObject("http://localhost:9091/payment/doPayment",payment,Payment.class);
////
////
////
//////        orderRepository.save(order);
//////        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
////
////
//        TransactionResponse obj = null;
//        try {
//
//            Payment paymentResponse = template.postForObject("http://localhost:9091/payment/doPayment", payment, Payment.class);
//            System.out.println("Hello2");
//            response =paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"There is a failure in payment api";
//
//
//            if (paymentResponse != null) {
//                System.out.println("Hello");
//                response = "Payment processing successful and order placed";
//            } else {
//                response = "There is a failure in the payment API";
//            }
//
//            orderRepository.save(order);
//            System.out.println("Hello");
//            obj = new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
//            return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
//        } catch (Exception e) {
//            // Handle exceptions that may occur during the REST call
//            // For example, you could log the error or return an appropriate error response
//            // depending on your application's requirements.
//            // ...
//
//        }
//
//         // Return an appropriate response or throw an exception if necessary
//        return obj;
//    }
//
//
//
//
//

}
