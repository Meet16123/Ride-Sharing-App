package com.meet.uber.uberapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {

    private Long id;

    private UserDto user;

    private  Double rating;

    private String vehicleId;

    private Boolean available;
}
