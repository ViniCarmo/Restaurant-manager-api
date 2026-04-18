package dev.vinicius.restaurant_management_api.repository;

import dev.vinicius.restaurant_management_api.entities.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Integer> {

}
