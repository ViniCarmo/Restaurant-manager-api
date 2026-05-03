package dev.vinicius.restaurant_management_api.controllers;

import dev.vinicius.restaurant_management_api.dto.LoginRequestDto;
import dev.vinicius.restaurant_management_api.dto.LoginResponseDto;
import dev.vinicius.restaurant_management_api.entities.User;
import dev.vinicius.restaurant_management_api.infra.security.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Realiza login e retorna token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Login ou senha incorretos")
    })
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        var authToken = new UsernamePasswordAuthenticationToken(loginRequestDto.emailOrLogin(), loginRequestDto.password());
        var auth = authenticationManager.authenticate(authToken);

        var tokenJwt = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(tokenJwt));
    }
}
