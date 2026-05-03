package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.dto.CustomerRequestDto;
import dev.vinicius.restaurant_management_api.dto.CustomerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.entities.Customer;
import dev.vinicius.restaurant_management_api.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateCustomerSuccessfully() {
        CustomerRequestDto customer = new CustomerRequestDto(
                "Teste",
                "Teste@gmail.com",
                "teste123",
                "senha",
                "Rua teste",
                "123456789-11"
        );
        when(passwordEncoder.encode(customer.password())).thenReturn("encodedPassword");

        customerService.createCustomer(customer);

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void shouldReturnCustomerWhenFoundById() {

        Customer customer = new Customer(
                1,
                "teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "123.456.789-00"
        );

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        CustomerResponseDto response = customerService.getCustomerById(1);

        assertEquals("teste", response.name());
        assertEquals("teste@email.com", response.email());
        assertEquals("teste123", response.login());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundById() {

        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            customerService.getCustomerById(99);
        });
    }

    @Test
    void shouldDeleteCustomerSuccessfully(){
        Customer customer = new Customer(
                1,
                "teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "123.456.789-00"
        );

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        customerService.deleteCustomerById(1);

        verify(customerRepository).deleteById(1);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundOnDelete(){
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            customerService.deleteCustomerById(99);
        });
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {

        Customer customer = new Customer(
                1,
                "teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "123.456.789-00"
        );

        CustomerRequestDto dto = new CustomerRequestDto(
                "teste atualizado",
                "testeatualizado@email.com",
                "teste456",
                "novaSenha",
                "Rua nova, 200",
                "987.654.321-00"
        );

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        CustomerResponseDto response = customerService.updateCustomer(1, dto);

        assertEquals("teste atualizado", response.name());
        assertEquals("testeatualizado@email.com", response.email());
        assertEquals("teste456", response.login());
        verify(customerRepository).save(customer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundOnUpdate() {

        CustomerRequestDto dto = new CustomerRequestDto(
                "teste atualizado",
                "testeatualizado@email.com",
                "teste456",
                "novaSenha",
                "Rua nova, 200",
                "987.654.321-00"
        );

        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            customerService.updateCustomer(99, dto);
        });
    }

    @Test
    void shouldUpdatePasswordSuccessfully(){
        Customer customer = new Customer(
                1,
                "teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "123.456.789-00"
        );

        UpdatePasswordRequestDto passwordRequestDto = new UpdatePasswordRequestDto(
                "novaSenha"
        );

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(passwordEncoder.encode("novaSenha")).thenReturn("senhaCriptografada");

        customerService.updatePassword(1, passwordRequestDto);

        verify(customerRepository).save(customer);
    }

    @Test
    void shouldReturnCustomerWhenFoundByName() {

        Customer customer = new Customer(
                1,
                "teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "123.456.789-00"
        );

        when(customerRepository.findByNameContainingIgnoreCase("teste")).thenReturn(List.of(customer));

        CustomerResponseDto response = customerService.findCustomerByName("teste").get(0);

        assertEquals("teste", response.name());
        assertEquals("teste@email.com", response.email());
        assertEquals("teste123", response.login());
    }
}
