package com.meet.uber.uberapplication.strategies.impl;

import com.meet.uber.uberapplication.entities.RideRequest;
import com.meet.uber.uberapplication.service.DistanceService;
import com.meet.uber.uberapplication.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

        private final DistanceService distanceService;
        private static final double SURGE_FACTOR = 1.5;

        @Override
        public double calculateFare(RideRequest rideRequest) {
            double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                    rideRequest.getDropOffLocation());
            return distance * RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
        }
}