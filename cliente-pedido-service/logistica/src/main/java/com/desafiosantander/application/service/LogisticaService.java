package com.desafiosantander.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.desafiosantander.application.dto.PedidoCriadoEvent;
import com.desafiosantander.infrastructure.PedidoClient;

@ApplicationScoped
public class LogisticaService {

    @Inject
    PedidoClient pedidoClient;

    public void processar(PedidoCriadoEvent evento) {
        pedidoClient.atualizarStatus(evento.getId(), "EM_TRANSPORTE");
    }
}