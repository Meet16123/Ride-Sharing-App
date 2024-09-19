package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.entities.RideRequest;
import com.meet.uber.uberapplication.repository.RideRequestRepository;
import com.meet.uber.uberapplication.service.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow( () -> new RuntimeException("Ride request not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow( () -> new RuntimeException("Ride request not found with id: " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
