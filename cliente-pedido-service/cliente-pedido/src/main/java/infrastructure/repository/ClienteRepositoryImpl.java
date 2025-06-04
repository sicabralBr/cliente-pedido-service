package infrastructure.repository;

import com.desafiosantander.domain.model.Cliente;
import com.desafiosantander.domain.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    @Transactional
    public void persist(Cliente cliente) {
        cliente.persist();
    }

    @Override
    @Transactional
    public void update(Cliente cliente) {
        cliente.persistAndFlush();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return Cliente.findByIdOptional(id);
    }

    @Override
    public List<Cliente> findAll() {
        return Cliente.listAll();
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        cliente.delete();
    }
}
