package dev.vinicius.restaurant_management_api.controllers;

import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerRequestDto;
import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.service.RestaurantOwnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant-owners")
public class RestaurantOwnerController {

    private final RestaurantOwnerService restaurantOwnerService;

    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    @PostMapping
    public ResponseEntity<Void> createRestaurantOwner(@RequestBody @Valid RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        restaurantOwnerService.createRestaurantOwner(restaurantOwnerRequestDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantOwnerById(@PathVariable Integer id) {
        restaurantOwnerService.deleteRestaurantOwnerById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantOwnerResponseDto> getRestaurantOwnerById(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantOwnerService.getRestaurantOwnerById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<RestaurantOwnerResponseDto>> getRestaurantOwnerByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantOwnerService.getRestaurantOwnerByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantOwnerResponseDto> updateRestaurantOwner(@PathVariable Integer id, @RequestBody @Valid RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        return ResponseEntity.ok(restaurantOwnerService.updateRestaurantOwner(id, restaurantOwnerRequestDto));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Integer id, @RequestBody @Valid UpdatePasswordRequestDto updatePasswordRequestDto) {
        restaurantOwnerService.updatePassword(id, updatePasswordRequestDto);
        return ResponseEntity.ok().build();
    }
}