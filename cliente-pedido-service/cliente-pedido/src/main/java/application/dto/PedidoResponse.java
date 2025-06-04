package application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
    public Long id;
    public Long clienteId;
    public String status;
    public LocalDateTime dataCriacao;
    public List<ItemPedidoResponse> itens;
}
