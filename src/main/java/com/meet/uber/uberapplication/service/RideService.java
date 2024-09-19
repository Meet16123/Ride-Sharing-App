package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.RideRequest;
import com.meet.uber.uberapplication.entities.Rider;
import com.meet.uber.uberapplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
