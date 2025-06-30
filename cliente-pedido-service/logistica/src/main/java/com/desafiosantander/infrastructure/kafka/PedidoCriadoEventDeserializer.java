import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PedidoCriadoEventDeserializer extends ObjectMapperDeserializer<PedidoCriadoEvent> {
    public PedidoCriadoEventDeserializer() {
        super(PedidoCriadoEvent.class);
    }
}
