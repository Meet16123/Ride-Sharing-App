package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.User;
import com.meet.uber.uberapplication.entities.Wallet;
import com.meet.uber.uberapplication.entities.WalletTransaction;
import com.meet.uber.uberapplication.entities.enums.TransactionMethod;
import com.meet.uber.uberapplication.entities.enums.TransactionType;
import com.meet.uber.uberapplication.exceptions.ResourceNotFoundException;
import com.meet.uber.uberapplication.repository.WalletRepository;
import com.meet.uber.uberapplication.service.WalletService;
import com.meet.uber.uberapplication.service.WalletTransactionService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Builder
public class WalletServiceImpl implements WalletService {

    private final WalletTransactionService walletTransactionService;

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();


//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow
                (() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow
                (() -> new ResourceNotFoundException("Wallet not found with user id: " + user.getId()));
    }
}
