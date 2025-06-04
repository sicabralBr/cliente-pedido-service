package application.service;

import com.desafiosantander.application.dto.PedidoRequest;
import com.desafiosantander.application.dto.PedidoResponse;
import com.desafiosantander.application.event.PedidoCriadoEvent;
import com.desafiosantander.application.mapper.PedidoMapper;
import com.desafiosantander.domain.model.Cliente;
import com.desafiosantander.domain.model.Pedido;
import com.desafiosantander.domain.repository.ClienteRepository;
import com.desafiosantander.domain.repository.PedidoRepository;
import com.desafiosantander.infrastructure.kafka.PedidoEventProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    PedidoEventProducer pedidoEventProducer;

    @Transactional
    public PedidoResponse criar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Pedido pedido = PedidoMapper.toEntity(request, cliente);
        pedidoRepository.persist(pedido);

        // Publicar evento após persistir o pedido
        PedidoCriadoEvent event = new PedidoCriadoEvent(
                pedido.id,
                cliente.id,
                pedido.getStatus().name()
        );
        pedidoEventProducer.enviar(event);

        return PedidoMapper.toResponse(pedido);
    }

    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<PedidoResponse> buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoMapper::toResponse);
    }

    @Transactional
    public void excluir(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.delete();
    }
}
