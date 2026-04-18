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
@Table(name = "Customers")
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    public Customer(Integer id, String name, String email, String login, String password, LocalDateTime modifiedDate, String address, String cpf) {
        super(id, name, email, login, password, modifiedDate, address);
        this.cpf = cpf;
    }

    private String cpf;
}
