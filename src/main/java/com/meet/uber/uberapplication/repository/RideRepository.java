package com.meet.uber.uberapplication.repository;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
