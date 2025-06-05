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
    Emitter<PedidoCriadoEvent> emitter;

    public void enviar(PedidoCriadoEvent evento) {
        emitter.send(evento); //
    }
}
