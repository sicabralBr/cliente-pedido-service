✅ 4. Estratégia de Monitoramento e Rastreamento Distribuído
Para garantir observabilidade e rastreamento dos serviços, a seguinte stack de ferramentas é proposta:

🛠️ Ferramentas
Prometheus: coleta de métricas de serviços via Micrometer (ou SmallRye Metrics no Quarkus).

Grafana: visualização das métricas e alertas.

Loki: coleta e visualização de logs centralizados.

Jaeger: rastreamento distribuído entre microsserviços via OpenTelemetry.

📊 Métricas capturadas
Serviço Métrica Descrição
cliente-pedido-service http_server_requests_seconds_count, http_server_requests_seconds_sum Latência e contagem de
requisições por endpoint
logistica-service kafka_messages_consumed_total Total de eventos Kafka processados
Kafka kafka.server.BrokerTopicMetrics Taxa de produção/consumo por tópico

📁 Logs
Todos os serviços configuram logs com JSON ou logfmt, enviados via stdout e capturados pelo Loki.

Exemplo de log:

json
Copiar
Editar
{
"level": "INFO",
"timestamp": "2025-06-05T10:00:00Z",
"service": "cliente-pedido-service",
"message": "Pedido criado com ID=123"
}
🔗 Rastreamento entre serviços
Todos os microsserviços propagam headers traceparent (W3C Trace Context).

Cada requisição HTTP ou Kafka carrega um TraceId, permitindo visualizar toda a jornada no Jaeger:

csharp
Copiar
Editar

[API → PedidoService → Kafka → LogisticaService → HTTP PUT status]