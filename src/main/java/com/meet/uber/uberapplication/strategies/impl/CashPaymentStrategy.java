package com.meet.uber.uberapplication.strategies.impl;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Payment;
import com.meet.uber.uberapplication.entities.enums.PaymentStatus;
import com.meet.uber.uberapplication.entities.enums.TransactionMethod;
import com.meet.uber.uberapplication.repository.PaymentRepository;
import com.meet.uber.uberapplication.service.WalletService;
import com.meet.uber.uberapplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
