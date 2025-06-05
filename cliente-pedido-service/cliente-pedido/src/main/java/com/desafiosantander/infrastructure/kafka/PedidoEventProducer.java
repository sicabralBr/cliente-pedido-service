package com.desafiosantander.infrastructure.kafka;

import com.desafiosantander.application.event.PedidoCriadoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PedidoEventProducer {

    @Inject
    @Channel("pedidos-out")
    Emitter<String> emitter;

    public void enviar(PedidoCriadoEvent evento) {
        String payload = String.format("{\"id\":%d,\"clienteId\":%d,\"status\":\"%s\"}",
                evento.id(), evento.clienteId(), evento.status());
        emitter.send(payload);
    }
}
