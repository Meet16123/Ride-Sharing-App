package com.meet.uber.uberapplication.repository;

import com.meet.uber.uberapplication.entities.Payment;
import com.meet.uber.uberapplication.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
