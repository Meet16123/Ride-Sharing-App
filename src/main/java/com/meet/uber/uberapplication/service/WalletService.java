package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.User;
import com.meet.uber.uberapplication.entities.Wallet;
import com.meet.uber.uberapplication.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);


}
