package com.desafiosantander.application.mapper;

import com.desafiosantander.application.dto.ClienteRequest;
import com.desafiosantander.application.dto.ClienteResponse;
import com.desafiosantander.domain.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequest dto) {
        return new Cliente(dto.nome, dto.email);
    }

    public static ClienteResponse toResponse(Cliente entity) {
        return new ClienteResponse(entity.id, entity.getNome(), entity.getEmail());
    }
}