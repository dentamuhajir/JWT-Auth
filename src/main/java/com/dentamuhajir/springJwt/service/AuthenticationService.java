package com.dentamuhajir.springJwt.service;

import com.dentamuhajir.springJwt.model.AuthenticationResponse;
import com.dentamuhajir.springJwt.model.User;
import com.dentamuhajir.springJwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(user.getRole());

        user = repository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);


    }
}
