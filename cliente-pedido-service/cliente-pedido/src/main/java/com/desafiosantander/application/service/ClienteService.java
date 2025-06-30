package com.desafiosantander.application.service;

import com.desafiosantander.application.dto.ClienteRequest;
import com.desafiosantander.application.dto.ClienteResponse;
import com.desafiosantander.application.mapper.ClienteMapper;
import com.desafiosantander.domain.model.Cliente;
import com.desafiosantander.domain.repository.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse cadastrar(ClienteRequest dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        clienteRepository.persist(cliente);
        return ClienteMapper.toResponse(cliente);
    }

    public Optional<ClienteResponse> buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        return Optional.ofNullable(cliente)
                .map(ClienteMapper::toResponse);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void editar(Long id, ClienteRequest dto) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
    }

    @Transactional
    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        clienteRepository.delete(cliente);
    }
}
