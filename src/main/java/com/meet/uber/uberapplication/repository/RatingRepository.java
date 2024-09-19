package com.meet.uber.uberapplication.repository;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Rating;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
