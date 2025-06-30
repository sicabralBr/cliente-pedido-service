package com.desafiosantander.domain.repository;

import com.desafiosantander.domain.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Optional<Cliente> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
