package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
