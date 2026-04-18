package dev.vinicius.restaurant_management_api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

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
}
