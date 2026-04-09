package com.kau.ecommerce.dto;

public record RegisterDTO(
        String name,
        String email,
        String cpf,
        String password
) {}