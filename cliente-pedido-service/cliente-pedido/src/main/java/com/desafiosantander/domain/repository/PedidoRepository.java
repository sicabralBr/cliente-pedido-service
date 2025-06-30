package com.desafiosantander.domain.repository;

import com.desafiosantander.domain.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public Optional<Pedido> findByIdOptional(Long id) {
        return findByIdOptional(id);
    }

    public List<Pedido> findAllPedidos() {
        return listAll();
    }
}
