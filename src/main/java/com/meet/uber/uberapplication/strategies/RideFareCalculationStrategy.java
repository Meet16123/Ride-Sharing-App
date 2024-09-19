package com.meet.uber.uberapplication.strategies;


import com.meet.uber.uberapplication.entities.RideRequest;

public interface RideFareCalculationStrategy {
    static final double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);

}
