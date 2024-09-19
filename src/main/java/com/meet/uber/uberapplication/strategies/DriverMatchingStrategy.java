package com.meet.uber.uberapplication.strategies;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
     List<Driver> findMatchingDriver(RideRequest rideRequest);
}
