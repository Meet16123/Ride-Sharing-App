package com.meet.uber.uberapplication.service;

import com.meet.uber.uberapplication.dto.DriverDto;
import com.meet.uber.uberapplication.dto.SignUpDto;
import com.meet.uber.uberapplication.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);
    UserDto signup(SignUpDto signUpDto);
    DriverDto onboardDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}

