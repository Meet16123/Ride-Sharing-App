package com.meet.uber.uberapplication.strategies;

import com.meet.uber.uberapplication.entities.enums.PaymentMethod;
import com.meet.uber.uberapplication.strategies.impl.CashPaymentStrategy;
import com.meet.uber.uberapplication.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy  walletPaymentStrategy;
    private final CashPaymentStrategy  cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
