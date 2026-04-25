package dev.vinicius.restaurant_management_api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDto(
        @NotBlank
        String newPassword
) {
}
