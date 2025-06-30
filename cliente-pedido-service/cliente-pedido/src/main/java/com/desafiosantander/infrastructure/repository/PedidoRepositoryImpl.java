package com.desafiosantander.domain.repository;

import com.desafiosantander.domain.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public Optional<Pedido> buscarPorId(Long id) {
        return findByIdOptional(id);
    }

}
