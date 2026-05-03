package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerRequestDto;
import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.entities.RestaurantOwner;
import dev.vinicius.restaurant_management_api.repository.RestaurantOwnerRepository;
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
class RestaurantOwnerServiceTest {

    @Mock
    private RestaurantOwnerRepository restaurantOwnerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RestaurantOwnerService restaurantOwnerService;

    @Test
    void shouldCreateRestaurantOwnerSuccessfully() {
        RestaurantOwnerRequestDto dto = new RestaurantOwnerRequestDto(
                "Teste",
                "teste@email.com",
                "teste123",
                "senha",
                "Rua teste, 100",
                "Restaurante Teste"
        );

        when(passwordEncoder.encode("senha")).thenReturn("senhaCriptografada");

        restaurantOwnerService.createRestaurantOwner(dto);

        verify(restaurantOwnerRepository).save(any(RestaurantOwner.class));
    }

    @Test
    void shouldReturnRestaurantOwnerWhenFoundById() {
        RestaurantOwner owner = new RestaurantOwner(
                1,
                "Teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "Restaurante Teste"
        );

        when(restaurantOwnerRepository.findById(1)).thenReturn(Optional.of(owner));

        RestaurantOwnerResponseDto response = restaurantOwnerService.getRestaurantOwnerById(1);

        assertEquals("Teste", response.name());
        assertEquals("teste@email.com", response.email());
        assertEquals("teste123", response.login());
        assertEquals("Restaurante Teste", response.restaurantName());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantOwnerNotFoundById() {
        when(restaurantOwnerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            restaurantOwnerService.getRestaurantOwnerById(99);
        });
    }

    @Test
    void shouldDeleteRestaurantOwnerSuccessfully() {
        RestaurantOwner owner = new RestaurantOwner(
                1,
                "Teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "Restaurante Teste"
        );

        when(restaurantOwnerRepository.findById(1)).thenReturn(Optional.of(owner));

        restaurantOwnerService.deleteRestaurantOwnerById(1);

        verify(restaurantOwnerRepository).deleteById(1);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantOwnerNotFoundOnDelete() {
        when(restaurantOwnerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            restaurantOwnerService.deleteRestaurantOwnerById(99);
        });
    }

    @Test
    void shouldUpdateRestaurantOwnerSuccessfully() {
        RestaurantOwner owner = new RestaurantOwner(
                1,
                "Teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "Restaurante Teste"
        );

        RestaurantOwnerRequestDto dto = new RestaurantOwnerRequestDto(
                "Teste Atualizado",
                "testeatualizado@email.com",
                "teste456",
                "novaSenha",
                "Rua nova, 200",
                "Restaurante Atualizado"
        );

        when(restaurantOwnerRepository.findById(1)).thenReturn(Optional.of(owner));

        RestaurantOwnerResponseDto response = restaurantOwnerService.updateRestaurantOwner(1, dto);

        assertEquals("Teste Atualizado", response.name());
        assertEquals("testeatualizado@email.com", response.email());
        assertEquals("teste456", response.login());
        assertEquals("Restaurante Atualizado", response.restaurantName());
        verify(restaurantOwnerRepository).save(owner);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantOwnerNotFoundOnUpdate() {
        RestaurantOwnerRequestDto dto = new RestaurantOwnerRequestDto(
                "Teste Atualizado",
                "testeatualizado@email.com",
                "teste456",
                "novaSenha",
                "Rua nova, 200",
                "Restaurante Atualizado"
        );

        when(restaurantOwnerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            restaurantOwnerService.updateRestaurantOwner(99, dto);
        });
    }

    @Test
    void shouldUpdatePasswordSuccessfully() {
        RestaurantOwner owner = new RestaurantOwner(
                1,
                "Teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "Restaurante Teste"
        );

        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("novaSenha");

        when(restaurantOwnerRepository.findById(1)).thenReturn(Optional.of(owner));
        when(passwordEncoder.encode("novaSenha")).thenReturn("senhaCriptografada");

        restaurantOwnerService.updatePassword(1, dto);

        verify(restaurantOwnerRepository).save(owner);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantOwnerNotFoundOnUpdatePassword() {
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("novaSenha");

        when(restaurantOwnerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            restaurantOwnerService.updatePassword(99, dto);
        });
    }

    @Test
    void shouldReturnRestaurantOwnerWhenFoundByName() {
        RestaurantOwner owner = new RestaurantOwner(
                1,
                "Teste",
                "teste@email.com",
                "teste123",
                "senhaCriptografada",
                LocalDateTime.now(),
                "Rua teste, 100",
                "Restaurante Teste"
        );

        when(restaurantOwnerRepository.findByNameContainingIgnoreCase("Teste")).thenReturn(List.of(owner));

        RestaurantOwnerResponseDto response = restaurantOwnerService.getRestaurantOwnerByName("Teste").get(0);

        assertEquals("Teste", response.name());
        assertEquals("teste@email.com", response.email());
        assertEquals("Restaurante Teste", response.restaurantName());
    }
}