package com.kau.ecommerce.controller;

import com.kau.ecommerce.dto.*;
import com.kau.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public TokenResponseDTO register(@RequestBody RegisterDTO dto){
        return new TokenResponseDTO(service.register(dto));
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginDTO dto){
        return new TokenResponseDTO(service.login(dto));
    }

    @PostMapping("/forgot-password")
    public String forgot(@RequestBody ForgotPasswordDTO dto){
        return service.forgotPassword(dto);
    }

    @PostMapping("/reset-password")
    public void reset(@RequestBody ResetPasswordDTO dto){
        service.resetPassword(dto);
    }
}