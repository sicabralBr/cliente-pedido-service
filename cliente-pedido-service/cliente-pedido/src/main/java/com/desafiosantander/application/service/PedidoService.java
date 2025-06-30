package com.desafiosantander.application.service;

import com.desafiosantander.application.dto.PedidoRequest;
import com.desafiosantander.application.dto.PedidoResponse;
import com.desafiosantander.application.event.PedidoCriadoEvent;
import com.desafiosantander.application.mapper.PedidoMapper;
import com.desafiosantander.domain.model.Cliente;
import com.desafiosantander.domain.model.Pedido;
import com.desafiosantander.domain.repository.ClienteRepository;
import com.desafiosantander.domain.repository.PedidoRepository;
import com.desafiosantander.infrastructure.kafka.PedidoEventProducer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    PedidoEventProducer pedidoEventProducer;

    @Transactional
    public PedidoResponse criar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId());
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        Pedido pedido = PedidoMapper.toEntity(request, cliente);
        pedidoRepository.persist(pedido);

        PedidoCriadoEvent event =
