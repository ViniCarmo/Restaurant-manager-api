package dev.vinicius.restaurant_management_api.controllers;

import dev.vinicius.restaurant_management_api.dto.CustomerRequestDto;
import dev.vinicius.restaurant_management_api.dto.CustomerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<CustomerResponseDto>> getCustomerByName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.findCustomerByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequestDto));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Integer id, @RequestBody @Valid UpdatePasswordRequestDto updatePasswordRequestDto) {
        customerService.updatePassword(id, updatePasswordRequestDto);
        return ResponseEntity.ok().build();
    }
}
