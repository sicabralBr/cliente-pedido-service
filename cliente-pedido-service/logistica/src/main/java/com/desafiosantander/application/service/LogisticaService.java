package com.desafiosantander.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

import com.desafiosantander.application.dto.PedidoCriadoEvent;
import com.desafiosantander.infrastructure.PedidoClient;

import org.jboss.logging.Logger;

@ApplicationScoped
public class LogisticaService {

    private static final Logger LOGGER = Logger.getLogger(LogisticaService.class);
    private static final String STATUS_EM_TRANSPORTE = "EM_TRANSPORTE";

    @Inject
    @RestClient
    PedidoClient pedidoClient;

    @Retry(maxRetries = 3, delay = 500)
    @Timeout(2000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 5000)
    @Fallback(fallbackMethod = "atualizarStatusFallback")
    public void processar(PedidoCriadoEvent evento) {
        LOGGER.infof("Processando evento logístico para pedido ID %d", evento.getId());

        pedidoClient.atualizarStatus(evento.getId(), STATUS_EM_TRANSPORTE);

        LOGGER.infof("Status do pedido %d atualizado para '%s'", evento.getId(), STATUS_EM_TRANSPORTE);
    }

    public void atualizarStatusFallback(PedidoCriadoEvent evento) {
        LOGGER.warnf("FALLBACK: Falha ao atualizar status do pedido %d. Será reprocessado futuramente.", evento.getId());
    }
}
