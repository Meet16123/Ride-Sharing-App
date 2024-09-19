package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.entities.Payment;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
