package com.kau.ecommerce.service;

import com.kau.ecommerce.config.JwtService;
import com.kau.ecommerce.dto.*;
import com.kau.ecommerce.entity.*;
import com.kau.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public String register(RegisterDTO dto) {

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .cpf(dto.cpf())
                .password(encoder.encode(dto.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    public String login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow();

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }

    public String forgotPassword(ForgotPasswordDTO dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow();

        String token = UUID.randomUUID().toString();

        PasswordResetToken reset = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiration(LocalDateTime.now().plusMinutes(30))
                .build();

        tokenRepository.save(reset);

        return token;
    }

    public void resetPassword(ResetPasswordDTO dto) {

        PasswordResetToken token = tokenRepository.findByToken(dto.token())
                .orElseThrow();

        if (token.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        User user = token.getUser();
        user.setPassword(encoder.encode(dto.newPassword()));

        userRepository.save(user);
    }
}