package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.dto.DriverDto;
import com.meet.uber.uberapplication.dto.RiderDto;
import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.Rating;
import com.meet.uber.uberapplication.entities.Ride;
import com.meet.uber.uberapplication.entities.Rider;
import com.meet.uber.uberapplication.exceptions.RuntimeConflictException;
import com.meet.uber.uberapplication.repository.DriverRepository;
import com.meet.uber.uberapplication.repository.RatingRepository;
import com.meet.uber.uberapplication.repository.RiderRepository;
import com.meet.uber.uberapplication.service.RatingService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final  DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Float rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new RuntimeException("Rating not found for ride with id: " + ride.getId())
        );

        if(ratingObj.getDriverRating() != null) {
            throw new RuntimeConflictException("Driver already rated for this ride");
        }

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average()
                .orElse(0.0);
        driver.setRating(newRating);

       Driver savedDriver =  driverRepository.save(driver);
       return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Float rating) {
        Rider rider = ride.getRider();
       Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new RuntimeException("Rating not found for ride with id: " + ride.getId())
         );

        if(ratingObj.getRiderRating() != null) {
            throw new RuntimeConflictException("Rider already rated for this ride");
        }

       ratingObj.setRiderRating(rating);
         ratingRepository.save(ratingObj);

       Double newRating = ratingRepository.findByRider(rider)
               .stream()
               .mapToDouble(Rating::getRiderRating)
               .average()
               .orElse(0.0);

       rider.setRating(newRating);
       Rider savedRider = riderRepository.save(rider);
       return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();

        ratingRepository.save(rating);
    }

}
