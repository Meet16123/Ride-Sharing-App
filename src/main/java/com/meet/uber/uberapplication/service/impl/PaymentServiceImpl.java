package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.entities.Payment;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.enums.PaymentStatus;
import com.meet.uber.uberapplication.repository.PaymentRepository;
import com.meet.uber.uberapplication.service.PaymentService;
import com.meet.uber.uberapplication.strategies.PaymentStrategyManager;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {

        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(   () -> new RuntimeException("Payment not found for ride: " + ride.getId()));

        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
