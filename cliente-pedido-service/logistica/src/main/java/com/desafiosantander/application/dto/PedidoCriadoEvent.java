package com.desafiosantander.application.dto;

import java.util.Objects;

public final class PedidoCriadoEvent {

    private final Long id;
    private final Long clienteId;
    private final String status;

    public PedidoCriadoEvent(Long id, Long clienteId, String status) {
        if (id == null || clienteId == null || status == null || status.isBlank()) {
            throw new IllegalArgumentException("Todos os campos do evento são obrigatórios e não podem ser nulos ou vazios.");
        }
        this.id = id;
        this.clienteId = clienteId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "PedidoCriadoEvent{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoCriadoEvent)) return false;
        PedidoCriadoEvent that = (PedidoCriadoEvent) o;
        return id.equals(that.id) &&
                clienteId.equals(that.clienteId) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, status);
    }
}
