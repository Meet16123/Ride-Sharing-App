package com.meet.uber.uberapplication.strategies.impl;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Payment;
import com.meet.uber.uberapplication.entities.Rider;
import com.meet.uber.uberapplication.entities.enums.PaymentStatus;
import com.meet.uber.uberapplication.entities.enums.TransactionMethod;
import com.meet.uber.uberapplication.repository.PaymentRepository;
import com.meet.uber.uberapplication.service.WalletService;
import com.meet.uber.uberapplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService  walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driverCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
