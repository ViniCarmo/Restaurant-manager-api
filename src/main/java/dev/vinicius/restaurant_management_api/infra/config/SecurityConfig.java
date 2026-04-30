package dev.vinicius.restaurant_management_api.infra.config;

import dev.vinicius.restaurant_management_api.infra.security.filter.SecurityFilter;
import dev.vinicius.restaurant_management_api.infra.security.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;
    private final UserDetailServiceImpl userDetailService;


    public SecurityConfig(SecurityFilter securityFilter, UserDetailServiceImpl userDetailService) {
        this.securityFilter = securityFilter;
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/auth").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
