package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.dto.DriverDto;
import com.meet.uber.uberapplication.dto.RiderDto;
import com.meet.uber.uberapplication.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Float rating);
    RiderDto rateRider(Ride ride, Float rating);

    void createNewRating(Ride ride);

}
