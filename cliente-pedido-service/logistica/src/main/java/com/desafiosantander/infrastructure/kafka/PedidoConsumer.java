package com.desafiosantander.infrastructure.kafka;


import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.desafiosantander.application.dto.PedidoCriadoEvent;
import com.desafiosantander.application.service.LogisticaService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PedidoConsumer {

    @Inject
    LogisticaService service;

    @Incoming("pedidos")
    @Blocking
    public void consumir(PedidoCriadoEvent event) {
        System.out.println("Evento recebido no logistica: " + event);
        service.processar(event);
    }
}