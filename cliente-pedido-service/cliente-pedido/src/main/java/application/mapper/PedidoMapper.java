package application.mapper;

import com.desafiosantander.application.dto.*;
import com.desafiosantander.domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static Pedido toEntity(PedidoRequest request, Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        List<ItemPedido> itens = request.itens.stream()
                .map(i -> new ItemPedido(pedido, i.produto, i.quantidade, i.precoUnitario))
                .collect(Collectors.toList());

        pedido.setItens(itens);
        return pedido;
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.id = pedido.id;
        response.clienteId = pedido.getCliente().id;
        response.dataCriacao = pedido.getDataCriacao();
        response.status = pedido.getStatus().name();
        response.itens = pedido.getItens().stream()
                .map(i -> {
                    ItemPedidoResponse r = new ItemPedidoResponse();
                    r.produto = i.getProduto();
                    r.quantidade = i.getQuantidade();
                    r.precoUnitario = i.getPrecoUnitario();
                    return r;
                })
                .collect(Collectors.toList());
        return response;
    }
}
