package com.desafiosantander.application.mapper;

import com.desafiosantander.application.dto.*;
import com.desafiosantander.domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public final class PedidoMapper {

    private PedidoMapper() {}

    public static Pedido toEntity(PedidoRequest request, Cliente cliente) {
        Pedido pedido = new Pedido(cliente);

        List<ItemPedido> itens = request.getItens().stream()
                .map(i -> new ItemPedido(pedido, i.getProduto(), i.getQuantidade(), i.getPrecoUnitario()))
                .collect(Collectors.toList());

        pedido.setItens(itens);
        return pedido;
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        List<ItemPedidoResponse> itensResponse = pedido.getItens().stream()
                .map(PedidoMapper::toItemResponse)
                .collect(Collectors.toList());

        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getStatus().name(),
                pedido.getDataCriacao(),
                itensResponse
        );
    }

    private static ItemPedidoResponse toItemResponse(ItemPedido item) {
        return new ItemPedidoResponse(
                item.getProduto(),
                item.getQuantidade(),
                item.getPrecoUnitario()
        );
    }
}
