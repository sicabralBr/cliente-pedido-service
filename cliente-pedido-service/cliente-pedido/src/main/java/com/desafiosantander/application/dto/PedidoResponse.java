package com.desafiosantander.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {

    private final Long id;
    private final Long clienteId;
    private final String status;
    private final LocalDateTime dataCriacao;
    private final List<ItemPedidoResponse> itens;

    public PedidoResponse(Long id, Long clienteId, String status, LocalDateTime dataCriacao, List<ItemPedidoResponse> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.itens = itens;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public List<ItemPedidoResponse> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "PedidoResponse{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", itens=" + itens +
                '}';
    }
}
