package com.fastech.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="PAYMENT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private double amount;

}
