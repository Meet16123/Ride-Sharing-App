package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.entities.WalletTransaction;
import com.meet.uber.uberapplication.repository.WalletTransactionRepository;
import com.meet.uber.uberapplication.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
