package dev.vinicius.restaurant_management_api.infra.security.filter;

import dev.vinicius.restaurant_management_api.infra.security.service.TokenService;
import dev.vinicius.restaurant_management_api.infra.security.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserDetailServiceImpl userDetailService;

    public SecurityFilter(TokenService tokenService, UserDetailServiceImpl userDetailService) {
        this.tokenService = tokenService;
        this.userDetailService = userDetailService;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recoverToken(request);
        if (tokenJWT != null) {
            var subject = tokenService.validateToken(tokenJWT);
            var user = userDetailService.loadUserByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
        }
filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
