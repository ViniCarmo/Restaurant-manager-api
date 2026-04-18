package dev.vinicius.restaurant_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RestaurantOwnerRequestDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "Restaurant name is required")
        String restaurantName
) {
}
