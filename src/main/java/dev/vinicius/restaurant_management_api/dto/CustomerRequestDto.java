package dev.vinicius.restaurant_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String Email,

        @NotBlank(message = "Login is required")
        String Login,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "CPF is required")
        String cpf) {
}
