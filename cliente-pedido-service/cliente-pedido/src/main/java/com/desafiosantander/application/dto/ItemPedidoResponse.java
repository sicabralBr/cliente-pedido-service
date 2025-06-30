package com.desafiosantander.application.dto;

import java.math.BigDecimal;

public class ItemPedidoResponse {

    private final String produto;
    private final Integer quantidade;
    private final BigDecimal precoUnitario;

    public ItemPedidoResponse(String produto, Integer quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemPedidoResponse{" +
                "produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
