package com.desafiosantander.application.event;

public record PedidoCriadoEvent(Long id, Long clienteId, String status) {
}
