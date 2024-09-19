package com.meet.uber.uberapplication.strategies;

import com.meet.uber.uberapplication.entities.Payment;

public interface PaymentStrategy {

    final Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
