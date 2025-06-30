package com.desafiosantander.infrastructure.kafka;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.desafiosantander.application.dto.PedidoCriadoEvent;
import com.desafiosantander.application.service.LogisticaService;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PedidoConsumer {

    private static final Logger LOGGER = Logger.getLogger(PedidoConsumer.class);

    @Inject
    LogisticaService logisticaService;

    @Incoming("pedidos-in")
    @Blocking
    public void consumir(PedidoCriadoEvent event) {
        try {
            LOGGER.infof("Evento recebido: %s", event);
            logisticaService.processar(event);
        } catch (Exception e) {
            LOGGER.errorf(e, "Erro ao processar evento de pedido ID %d", event.getId());
        }
    }
}
