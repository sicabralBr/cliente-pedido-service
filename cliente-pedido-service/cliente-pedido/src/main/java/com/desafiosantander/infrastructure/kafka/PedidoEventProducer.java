package com.desafiosantander.infrastructure.kafka;

import com.desafiosantander.application.event.PedidoCriadoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PedidoEventProducer {

    @Channel("pedidos")
    Emitter<String> emitter;

    public void enviar(PedidoCriadoEvent evento) {
        String payload = "{\"id\":" + evento.id() + ",\"clienteId\":" + evento.clienteId() + ",\"status\":\"" + evento.status() + "\"}";
        emitter.send(payload);
    }
}
