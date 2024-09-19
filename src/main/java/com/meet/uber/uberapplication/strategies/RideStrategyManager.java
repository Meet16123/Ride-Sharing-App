package com.meet.uber.uberapplication.strategies;

import com.meet.uber.uberapplication.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.meet.uber.uberapplication.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.meet.uber.uberapplication.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.meet.uber.uberapplication.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if(riderRating >= 4.5) {
            return highestRatedDriverStrategy;
        } else {
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        LocalTime surgeTimeStart = LocalTime.of(17, 30);
        LocalTime surgeTimeEnd = LocalTime.of(21, 30);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeTimeStart) && currentTime.isBefore(surgeTimeEnd);

        if(isSurgeTime) {
            return surgePricingFareCalculationStrategy;
        } else {
            return defaultFareCalculationStrategy;
        }
    }
}
