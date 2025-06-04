package com.desafiosantander.domain.repository;

import com.desafiosantander.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void persist(Cliente cliente);
    void update(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    void delete(Cliente cliente);
}