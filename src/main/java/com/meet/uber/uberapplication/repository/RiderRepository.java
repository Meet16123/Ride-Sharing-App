package com.meet.uber.uberapplication.repository;

import com.meet.uber.uberapplication.entities.Rider;
import com.meet.uber.uberapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
