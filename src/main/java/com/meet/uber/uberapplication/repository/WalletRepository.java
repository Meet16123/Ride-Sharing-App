package com.meet.uber.uberapplication.repository;

import com.meet.uber.uberapplication.entities.User;
import com.meet.uber.uberapplication.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
