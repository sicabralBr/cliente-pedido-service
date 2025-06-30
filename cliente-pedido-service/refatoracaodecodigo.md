public class ClienteService {

    private final ClienteRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(ClienteDTO clienteDTO) {
        validar(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO.getNome());
        repository.save(cliente);
        logger.info("Cliente cadastrado: {}", cliente.getNome());
    }

    private void validar(ClienteDTO clienteDTO) {
        if (clienteDTO == null || clienteDTO.getNome() == null || clienteDTO.getNome().isBlank()) {
            throw new DadosInvalidosException("Nome do cliente é obrigatório.");
        }
    }

}