package com.desafiosantander.domain.repository;

import com.desafiosantander.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    void persist(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<Pedido> findAll();
}