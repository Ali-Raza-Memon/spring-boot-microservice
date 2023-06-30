package com.fastech.orderservice.controller;

import com.fastech.orderservice.common.Payment;
import com.fastech.orderservice.entity.Order;
import com.fastech.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse saveOrder(@RequestBody TransactionRequest request){

        return orderService.saveOrder(request);
    }





}
