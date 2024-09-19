package com.meet.uber.uberapplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_rating_rider_id", columnList = "rider_id"),
        @Index(name = "idx_rating_driver_id", columnList = "driver_id")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Float driverRating;
    private Float riderRating;

}
