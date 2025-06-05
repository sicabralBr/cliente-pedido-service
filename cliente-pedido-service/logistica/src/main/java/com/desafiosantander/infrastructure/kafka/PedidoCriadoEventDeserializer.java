package com.desafiosantander.infrastructure.kafka;

import com.desafiosantander.application.dto.PedidoCriadoEvent;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoCriadoEventDeserializer extends ObjectMapperDeserializer<PedidoCriadoEvent> {
    public PedidoCriadoEventDeserializer() {
        super(PedidoCriadoEvent.class);
    }
}