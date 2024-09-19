package com.meet.uber.uberapplication.dto;

import com.meet.uber.uberapplication.entities.enums.TransactionMethod;
import com.meet.uber.uberapplication.entities.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletTransactionDto {
    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto walletDto;

    private LocalDateTime timeStamp;
}
