package com.meet.uber.uberapplication.service.impl;

import com.meet.uber.uberapplication.dto.DriverDto;
import com.meet.uber.uberapplication.dto.SignUpDto;
import com.meet.uber.uberapplication.dto.UserDto;
import com.meet.uber.uberapplication.entities.Driver;
import com.meet.uber.uberapplication.entities.User;
import com.meet.uber.uberapplication.entities.enums.Role;
import com.meet.uber.uberapplication.exceptions.RuntimeConflictException;
import com.meet.uber.uberapplication.repository.UserRepository;
import com.meet.uber.uberapplication.security.JWTService;
import com.meet.uber.uberapplication.service.AuthService;
import com.meet.uber.uberapplication.service.DriverService;
import com.meet.uber.uberapplication.service.RiderService;
import com.meet.uber.uberapplication.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.meet.uber.uberapplication.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = ((User) authentication.getPrincipal());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[] {accessToken, refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);

        if(user != null) {
            throw new RuntimeConflictException("User already exists with email: " + signUpDto.getEmail());
        }

        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);

        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeConflictException("User not found with id: " + userId)
        );
        if(user.getRoles().contains(DRIVER)) {
            throw new RuntimeException("User is already a driver with id: " + userId);
        }

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(DRIVER);
        userRepository.save(user);

        Driver savedDriver = driverService.createNewDriver(createDriver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with id: " + userId)
        );
        return jwtService.generateAccessToken(user);
    }
}
