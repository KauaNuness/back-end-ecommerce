package com.kau.ecommerce.dto;

public record ResetPasswordDTO(
        String token,
        String newPassword
) {
}
