package dev.vinicius.restaurant_management_api.repository;

import dev.vinicius.restaurant_management_api.dto.CustomerResponseDto;
import dev.vinicius.restaurant_management_api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByName(String name);
}
