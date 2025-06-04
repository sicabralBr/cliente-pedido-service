package application.service;

import com.desafiosantander.application.dto.ClienteRequest;
import com.desafiosantander.application.dto.ClienteResponse;
import com.desafiosantander.application.mapper.ClienteMapper;
import com.desafiosantander.domain.model.Cliente;
import com.desafiosantander.domain.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public ClienteResponse cadastrar(ClienteRequest dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        clienteRepository.persist(cliente);
        return ClienteMapper.toResponse(cliente);
    }

    public Optional<ClienteResponse> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toResponse);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void editar(Long id, ClienteRequest dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setNome(dto.nome);
        cliente.setEmail(dto.email);
        clienteRepository.update(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}
