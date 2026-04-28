package dev.vinicius.restaurant_management_api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RestaurantOwners")
@DiscriminatorValue("RESTAURANT_OWNER")
public class RestaurantOwner extends User {

    public RestaurantOwner(Integer id, String name, String email, String login, String password, LocalDateTime modifiedDate, String address, String restaurantName) {
        super(id, name, email, login, password, modifiedDate, address);
        this.restaurantName = restaurantName;
    }

    private String restaurantName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
