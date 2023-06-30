package com.fastech.orderservice.controller;

import com.fastech.orderservice.common.Payment;
import com.fastech.orderservice.entity.Order;

public class TransactionResponse {
    private Order order;
    private int amount;
    private String transactionId;
}
