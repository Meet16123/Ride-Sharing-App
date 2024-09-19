package com.meet.uber.uberapplication.strategies.impl;

import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.RideRequest;
import com.meet.uber.uberapplication.repository.DriverRepository;
import com.meet.uber.uberapplication.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional()
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
