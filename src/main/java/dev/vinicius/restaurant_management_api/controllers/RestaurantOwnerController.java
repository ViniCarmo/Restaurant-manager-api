package dev.vinicius.restaurant_management_api.controllers;

import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerRequestDto;
import dev.vinicius.restaurant_management_api.dto.RestaurantOwnerResponseDto;
import dev.vinicius.restaurant_management_api.dto.UpdatePasswordRequestDto;
import dev.vinicius.restaurant_management_api.service.RestaurantOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Cria um novo proprietário de restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proprietário de restaurante criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado")
    })
    @PostMapping
    public ResponseEntity<Void> createRestaurantOwner(@RequestBody @Valid RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        restaurantOwnerService.createRestaurantOwner(restaurantOwnerRequestDto);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Deleta um proprietário de restaurante por ID")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Proprietário de restaurante deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário de restaurante não encontrado")
    })
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantOwnerById(@PathVariable Integer id) {
        restaurantOwnerService.deleteRestaurantOwnerById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Busca um proprietário de restaurante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietário de restaurante encontrado"),
            @ApiResponse(responseCode = "404", description = "Proprietário de restaurante não encontrado")
    })
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantOwnerResponseDto> getRestaurantOwnerById(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantOwnerService.getRestaurantOwnerById(id));
    }

    @Operation(summary = "Busca proprietários de restaurante por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietários de restaurante encontrados")
    })
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/name")
    public ResponseEntity<List<RestaurantOwnerResponseDto>> getRestaurantOwnerByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantOwnerService.getRestaurantOwnerByName(name));
    }

    @Operation(summary = "Atualiza dados de um proprietário de restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietário de restaurante atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário de restaurante não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantOwnerResponseDto> updateRestaurantOwner(@PathVariable Integer id, @RequestBody @Valid RestaurantOwnerRequestDto restaurantOwnerRequestDto) {
        return ResponseEntity.ok(restaurantOwnerService.updateRestaurantOwner(id, restaurantOwnerRequestDto));
    }

    @Operation(summary = "Atualiza a senha de um proprietário de restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário de restaurante não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Integer id, @RequestBody @Valid UpdatePasswordRequestDto updatePasswordRequestDto) {
        restaurantOwnerService.updatePassword(id, updatePasswordRequestDto);
        return ResponseEntity.ok().build();
    }
}