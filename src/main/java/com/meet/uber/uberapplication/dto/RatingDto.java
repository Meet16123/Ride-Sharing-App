package com.meet.uber.uberapplication.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private Long rideId;
    private Float rating;
}
