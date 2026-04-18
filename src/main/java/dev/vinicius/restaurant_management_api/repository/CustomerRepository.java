package dev.vinicius.restaurant_management_api.repository;

import dev.vinicius.restaurant_management_api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Integer, Customer> {
}
