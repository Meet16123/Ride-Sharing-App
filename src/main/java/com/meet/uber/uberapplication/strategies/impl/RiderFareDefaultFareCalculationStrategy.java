package com.meet.uber.uberapplication.strategies.impl;

import com.meet.uber.uberapplication.entities.RideRequest;
import com.meet.uber.uberapplication.service.DistanceService;
import com.meet.uber.uberapplication.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        double total = distance * RIDE_FARE_MULTIPLIER;
        //round off to 2 decimal places after decimal points
        return Math.round(total * 100.0) / 100.0;
    }
}
