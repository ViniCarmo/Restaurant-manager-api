package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.dto.CustomerRequestDto;
import dev.vinicius.restaurant_management_api.dto.CustomerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.entities.Customer;
import dev.vinicius.restaurant_management_api.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer(
                null,
                customerRequestDto.name(),
                customerRequestDto.email(),
                customerRequestDto.login(),
                customerRequestDto.password(),
                LocalDateTime.now(),
                customerRequestDto.address(),
                customerRequestDto.cpf()
        );
        customerRepository.save(customer);
    }

    public CustomerResponseDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return new CustomerResponseDto(
                customer.getName(),
                customer.getEmail(),
                customer.getLogin(),
                customer.getModifiedDate(),
                customer.getAddress(),
                customer.getCpf()
        );
    }

    public void deleteCustomerById(Integer id) {
       customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customerRepository.deleteById(id);
    }

    public CustomerResponseDto updateCustomer(Integer id, CustomerRequestDto customerRequestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(customerRequestDto.name());
        customer.setEmail(customerRequestDto.email());
        customer.setLogin(customerRequestDto.login());
        customer.setModifiedDate(LocalDateTime.now());
        customer.setAddress(customerRequestDto.address());
        customer.setCpf(customerRequestDto.cpf());
        customerRepository.save(customer);
        return new CustomerResponseDto(
                customer.getName(),
                customer.getEmail(),
                customer.getLogin(),
                customer.getModifiedDate(),
                customer.getAddress(),
                customer.getCpf()
        );
    }

    public void updatePassword(Integer id, UpdatePasswordRequestDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customer.setPassword(dto.newPassword());
        customer.setModifiedDate(LocalDateTime.now());
        customerRepository.save(customer);
    }
    public List<CustomerResponseDto> findCustomerByName(String name){
        return customerRepository.findCustomerByName(name).stream().map(customer -> new CustomerResponseDto(
                customer.getName(),
                customer.getEmail(),
                customer.getLogin(),
                customer.getModifiedDate(),
                customer.getAddress(),
                customer.getCpf()
        )).toList();

    }
}

