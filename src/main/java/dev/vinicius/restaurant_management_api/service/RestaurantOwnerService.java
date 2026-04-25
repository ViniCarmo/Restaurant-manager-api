package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerRequestDto;
import dev.vinicius.restaurant_management_api.entities.RestaurantOwner;
import dev.vinicius.restaurant_management_api.repository.RestaurantOwnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RestaurantOwnerService {

    private final RestaurantOwnerRepository restaurantOwnerRepository;

    public RestaurantOwnerService(RestaurantOwnerRepository restaurantOwnerRepository) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
    }

    private void createRestaurantOwner(RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        RestaurantOwner restaurantOwner = new RestaurantOwner(
                null,
                restaurantOwnerRequestDto.name(),
                restaurantOwnerRequestDto.email(),
                restaurantOwnerRequestDto.login(),
                restaurantOwnerRequestDto.password(),
                LocalDateTime.now(),
                restaurantOwnerRequestDto.address(),
                restaurantOwnerRequestDto.restaurantName()
        );

        restaurantOwnerRepository.save(restaurantOwner);
    }
    private void deleteRestaurantOwnerById(Integer id) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant owner not found"));
        restaurantOwnerRepository.deleteById(id);
    }

}
