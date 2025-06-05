✅ 5. Estratégia de Alta Disponibilidade e Resiliência
🔄 Práticas utilizadas:
Técnica	Aplicação
Retries	Uso de @Retry do MicroProfile Fault Tolerance no PedidoClient
Circuit Breaker	@CircuitBreaker aplicado ao mesmo método para evitar cascata de falhas
Timeouts	Definido tempo máximo para chamadas HTTP (@Timeout ou no RestClientBuilder)
Fallback	Métodos alternativos via @Fallback para manter sistema funcional
Mensageria assíncrona	Kafka entre serviços desacopla fluxos e melhora escalabilidade
Deployment resiliente	Horizontal scaling, readiness/liveness probes no Kubernetes ou Docker Compose