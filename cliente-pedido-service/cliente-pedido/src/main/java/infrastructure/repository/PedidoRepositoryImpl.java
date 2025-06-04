package infrastructure.repository;

import com.desafiosantander.domain.model.Pedido;
import com.desafiosantander.domain.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PedidoRepositoryImpl implements PedidoRepository {

    @Override
    @Transactional
    public void persist(Pedido pedido) {
        pedido.persist();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Pedido.findByIdOptional(id);
    }

    @Override
    public List<Pedido> findAll() {
        return Pedido.listAll();
    }
}