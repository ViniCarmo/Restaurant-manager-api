package dev.vinicius.restaurant_management_api.dto;

public record RestaurantOwnerRequestDto(
        String name,
        String email,
        String login,
        String password,
        String address,
        String restaurantName
) {
}
