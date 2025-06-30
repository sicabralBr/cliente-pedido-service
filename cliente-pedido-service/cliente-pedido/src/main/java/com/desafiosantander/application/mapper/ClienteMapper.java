package com.desafiosantander.application.mapper;

import com.desafiosantander.application.dto.ClienteRequest;
import com.desafiosantander.application.dto.ClienteResponse;
import com.desafiosantander.domain.model.Cliente;

public final class ClienteMapper {

    private ClienteMapper() {}

    public static Cliente toEntity(ClienteRequest dto) {
        if (dto == null) return null;
        return new Cliente(dto.getNome(), dto.getEmail());
    }

    public static ClienteResponse toResponse(Cliente entity) {
        if (entity == null) return null;
        return new ClienteResponse(entity.getId(), entity.getNome(), entity.getEmail());
    }
}
