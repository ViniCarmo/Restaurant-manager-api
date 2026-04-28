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
@Table(name = "Customers")
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    public Customer(Integer id, String name, String email, String login, String password, LocalDateTime modifiedDate, String address, String cpf) {
        super(id, name, email, login, password, modifiedDate, address);
        this.cpf = cpf;
    }

    private String cpf;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
