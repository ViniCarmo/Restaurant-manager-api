package dev.vinicius.restaurant_management_api.dto;

import java.time.LocalDateTime;

public record CustomerResponseDto(
        String name,
        String email,
        String login,
        LocalDateTime modifiedDate,
        String address,
        String cpf
) {
}
