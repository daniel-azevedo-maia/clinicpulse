package com.clinicpulse.auth.application.service;

import com.clinicpulse.auth.application.dto.*;
import com.clinicpulse.auth.domain.model.AppUser;
import com.clinicpulse.auth.domain.model.Role;

import com.clinicpulse.auth.domain.repository.AppUserRepository;
import com.clinicpulse.auth.infrastructure.exception.InvalidCredentialsException;
import com.clinicpulse.auth.infrastructure.exception.UserNotFoundException;
import com.clinicpulse.auth.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public JwtAuthenticationResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        AppUser user = AppUser.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf("ROLE_" + request.getRole().toUpperCase()))
                .build();


        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return JwtAuthenticationResponse.withToken(token);

    }

    public JwtAuthenticationResponse login(UserLoginRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Credenciais inválidas.");
        }

        String token = jwtService.generateToken(user);
        return JwtAuthenticationResponse.withToken(token);

    }
}
