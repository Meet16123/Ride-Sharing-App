package com.meet.uber.uberapplication.service;


import com.meet.uber.uberapplication.dto.DriverDto;
import com.meet.uber.uberapplication.dto.RideDto;
import com.meet.uber.uberapplication.dto.RideRequestDto;
import com.meet.uber.uberapplication.dto.RiderDto;
import com.meet.uber.uberapplication.entities.Rider;
import com.meet.uber.uberapplication.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Float rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
