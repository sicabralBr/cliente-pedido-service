package com.desafiosantander.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.desafiosantander.application.dto.PedidoCriadoEvent;
import com.desafiosantander.infrastructure.PedidoClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class LogisticaService {

    @Inject
    @RestClient
    PedidoClient pedidoClient;

    public void processar(PedidoCriadoEvent evento) {
        pedidoClient.atualizarStatus(evento.getId(), "EM_TRANSPORTE");
    }
}