package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerRequestDto;
import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.entities.RestaurantOwner;
import dev.vinicius.restaurant_management_api.repository.RestaurantOwnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    private RestaurantOwnerResponseDto getRestaurantOwnerById(Integer id) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant owner not found"));
        return new RestaurantOwnerResponseDto(
                restaurantOwner.getName(),
                restaurantOwner.getEmail(),
                restaurantOwner.getLogin(),
                restaurantOwner.getModifiedDate(),
                restaurantOwner.getAddress(),
                restaurantOwner.getRestaurantName()
        );
    }

    public List<RestaurantOwnerResponseDto> getRestaurantOwnerByName(String name) {
        return restaurantOwnerRepository.getRestaurantOwnerByName(name).stream()
                .map(restaurantOwner -> new RestaurantOwnerResponseDto(
                        restaurantOwner.getName(),
                        restaurantOwner.getEmail(),
                        restaurantOwner.getLogin(),
                        restaurantOwner.getModifiedDate(),
                        restaurantOwner.getAddress(),
                        restaurantOwner.getRestaurantName()
                )).toList();
    }

    public RestaurantOwnerResponseDto updateRestaurantOwner(Integer id, RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant owner not found"));

        restaurantOwner.setName(restaurantOwnerRequestDto.name());
        restaurantOwner.setEmail(restaurantOwnerRequestDto.email());
        restaurantOwner.setLogin(restaurantOwnerRequestDto.login());
        restaurantOwner.setModifiedDate(LocalDateTime.now());
        restaurantOwner.setAddress(restaurantOwnerRequestDto.address());
        restaurantOwner.setRestaurantName(restaurantOwnerRequestDto.restaurantName());

        restaurantOwnerRepository.save(restaurantOwner);
        return new RestaurantOwnerResponseDto(
                restaurantOwner.getName(),
                restaurantOwner.getEmail(),
                restaurantOwner.getLogin(),
                restaurantOwner.getModifiedDate(),
                restaurantOwner.getAddress(),
                restaurantOwner.getRestaurantName()
        );
    }

     public void updatePassword(Integer id, UpdatePasswordRequestDto updatePasswordRequestDto) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant owner not found"));
        restaurantOwner.setPassword(updatePasswordRequestDto.newPassword());
        restaurantOwner.setModifiedDate(LocalDateTime.now());
        restaurantOwnerRepository.save(restaurantOwner);
    }
}
