package application.event;

public record PedidoCriadoEvent(Long id, Long clienteId, String status) {}
