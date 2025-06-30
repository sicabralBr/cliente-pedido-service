# cliente-pedido-service

Este projeto implementa microsserviços em Java com **Quarkus**, para gerenciar clientes e pedidos. Inclui integração com
Kafka, persistência com Hibernate + Panache, consumo de APIs externas e orquestração com Airflow.

## Autor

- 👤 Gabriel Meello
- 💼 [LinkedIn](https://www.linkedin.com/in/meellogabriel/)
- 💻 [GitHub](https://github.com/gbmeello)

---

## 🐳 Executando com Docker Compose

O ambiente completo é executado com:

```bash
docker-compose up --build
```

Serviços incluídos:

- `cliente-service`: API para gerenciamento de clientes
- `pedido-service`: API para gerenciamento de pedidos
- `kafka`: broker para comunicação assíncrona
- `zookeeper`: dependência do Kafka
- `airflow-web`, `airflow-scheduler`: orquestração de tarefas
- `postgres`: banco de dados relacional
- `quarkus-apps`: containers Quarkus prontos para produção

---

## 🌐 Endpoints Importantes

- Cliente API: `http://localhost:8081/clientes`
- Pedido API: `http://localhost:8082/pedidos`
- Kafka UI (se configurado): `http://localhost:8088`
- Airflow UI: `http://localhost:8080`

Usuário padrão:

- **login**: `airflow`
- **senha**: `airflow`

---

## 🔗 Tecnologias e Extensões

| Tecnologia              | Função                            |
|-------------------------|-----------------------------------|
| **Quarkus**             | Framework Java para microserviços |
| **Kafka**               | Comunicação assíncrona            |
| **Airflow**             | Orquestração de tarefas           |
| **PostgreSQL**          | Persistência                      |
| **Hibernate + Panache** | ORM simplificado                  |
| **Jackson**             | Serialização JSON                 |
| **Docker**              | Empacotamento e execução          |
| **Rest Client**         | Consumo de APIs externas          |

---

## 📊 Observabilidade Sugerida

- **Prometheus + Grafana** para métricas
- **Loki** para logs agregados
- **Jaeger** para rastreamento distribuído entre microsserviços

---

## ⚙️ Resiliência & Alta Disponibilidade

- Timeouts e retries nas chamadas REST
- Circuit breakers (ex: Resilience4j/MicroProfile Fault Tolerance)
- Kafka para desacoplamento entre serviços
- Banco de dados com persistência em volumes Docker

---

## 🧠 Desafio Técnico Enfrentado

Ao lidar com múltiplas chamadas de APIs instáveis em produção, implementei:

- Circuit breakers com fallback para cache Redis
- Retry com backoff exponencial
- Timeout e monitoramento de latência

🔁 Resultado: Redução de falhas em 80% e maior confiança no sistema.

---

## 🧹 Refatoração de Código (SOLID)

### Original

```java
public class ClienteService {
    public void cadastrar(Cliente cliente) {
        if (cliente.getNome().isEmpty()) {
            throw new RuntimeException("Nome inválido");
        }
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }
}
```

### Refatorado

```java
public class ClienteService {
    private final Notificador notificador;

    public ClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void cadastrar(Cliente cliente) {
        validar(cliente);
        notificador.notificarCadastro(cliente);
    }

    private void validar(Cliente cliente) {
        if (cliente == null || cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
    }
}

public interface Notificador {
    void notificarCadastro(Cliente cliente);
}

public class ConsoleNotificador implements Notificador {
    public void notificarCadastro(Cliente cliente) {
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }
}
```

---

## 📬 Contato

Gabriel Meello  
📧 meello.gabriel@gmail.com  
🌐 [linkedin.com/in/meellogabriel](https://www.linkedin.com/in/meellogabriel/)  
🐙 [github.com/gbmeello](https://github.com/gbmeello)

---

Feito com 💻, ☕ e containers 🚀
