# cliente-pedido-service

Este projeto implementa microsserviços em Java com **Quarkus**, para gerenciar clientes e pedidos. Inclui integração com Kafka, persistência com Hibernate + Panache, consumo de APIs externas e orquestração com Airflow.

## Autor

- 👤 Gabriel Meello
- 💼 [LinkedIn](https://www.linkedin.com/in/meellogabriel/)
- 💻 [GitHub](https://github.com/gbmeello)

---

## 📦 Funcionalidades

- Cadastro e gerenciamento de **Clientes** e **Pedidos**
- Integração assíncrona via **Apache Kafka**
- Consumo de API externa com **RestClient**
- Orquestração com **Apache Airflow**
- Armazenamento em **H2 Database**
- Serialização com **Jackson**
- Deploy nativo com **GraalVM**

---

## 🚀 Executando em modo Dev

Com hot reload:

```bash
./mvnw quarkus:dev
```

Acesse o Dev UI: [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/)

---

## 📦 Empacotando e executando

### JAR tradicional:

```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Über-JAR:

```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar
```

---

## 🧊 Executável Nativo

### Requisitos: GraalVM ou container

```bash
./mvnw package -Dnative
```

ou via container:

```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Executar:

```bash
./target/cliente-pedido-service-1.0.0-SNAPSHOT-runner
```

---

## 🔗 Integrações e Extensões Usadas

| Recurso | Descrição |
|--------|----------|
| [REST Client](https://quarkus.io/guides/rest-client) | Consumo de APIs externas |
| [REST](https://quarkus.io/guides/rest) | Exposição de endpoints HTTP |
| [Hibernate ORM + Panache](https://quarkus.io/guides/hibernate-orm-panache) | Persistência com modelo simplificado |
| [Kafka](https://quarkus.io/guides/kafka) | Comunicação assíncrona entre serviços |
| [Jackson](https://quarkus.io/guides/rest#json-serialisation) | Serialização de JSON |
| [H2 Database](https://quarkus.io/guides/datasource) | Banco de dados em memória para testes |

---

## 🧪 Testes

Para executar os testes automatizados:

```bash
./mvnw test
```

---

## 🧰 Estrutura dos Serviços

- `cliente-service`: Gerenciamento de clientes
- `pedido-service`: Gerenciamento de pedidos
- `airflow-orchestrator`: Orquestra coleta de dados de API externa e envia para Kafka

---

## 📊 Monitoramento e Observabilidade

Recomendado:

- **Prometheus + Grafana**: para métricas (latência, throughput, erros)
- **Loki**: logs agregados por serviço
- **Jaeger**: rastreamento distribuído com trace ID via headers
- **Micrometer** (integrado ao Quarkus) para exposição de métricas

---

## ☁️ Alta Disponibilidade e Resiliência

- `Retry` com `@Retry` do MicroProfile Fault Tolerance
- `CircuitBreaker` para falhas consecutivas
- `Timeout` e `Fallback` configurados
- Mensageria assíncrona com Kafka: garante resiliência e desacoplamento

---

## 🧠 Desafio Técnico Enfrentado

Em um sistema legado com múltiplos endpoints REST integrando sistemas bancários, enfrentei problemas com **baixa resiliência** devido a timeouts em cascata. Refatorei os clientes HTTP para usar o padrão `Circuit Breaker` + `Retry` com fallback automático para cache local em Redis. Isso reduziu falhas em 80% e melhorou a experiência do usuário.

---

## 🔍 Análise e Refatoração de Código

### Código original:
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

### Código refatorado:
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

**Princípios aplicados**:
- ✅ SRP: responsabilidade única
- ✅ DIP: dependência de abstrações
- ✅ LSP: permite substituição de Notificador
- ✅ Tratamento robusto de exceções

---

## 📬 Contato

Gabriel Meello  
📧 meello.gabriel@gmail.com  
🌐 [linkedin.com/in/meellogabriel](https://www.linkedin.com/in/meellogabriel/)  
🐙 [github.com/gbmeello](https://github.com/gbmeello)

---

Feito com 💻, ☕ e muita dedicação 🚀
