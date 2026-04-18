package dev.vinicius.restaurant_management_api.repository;

import dev.vinicius.restaurant_management_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Integer, User> {
    String findByName(String name);

}
