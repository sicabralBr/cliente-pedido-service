✅ Desafio Técnico Real
Situação:
Durante o desenvolvimento de uma plataforma de onboarding digital com reconhecimento facial, enfrentamos problemas de latência e timeout nas chamadas a APIs de terceiros (provedores de biometria), principalmente em horários de pico.

Cenário:

A API externa tinha SLA frágil e variabilidade alta de resposta.

Nossos serviços estavam síncronos e bloqueantes, esperando a resposta do provedor.

Isso afetava a UX e gerava falhas em cascata nos demais serviços.

Decisão:
Refatoramos o fluxo para ser 100% assíncrono e orientado a eventos:

Transformamos o serviço que chamava a API de biometria em um worker Kafka consumer.

O frontend apenas disparava o evento “IniciarValidaçãoBiometrica” e recebia atualizações via WebSocket ou polling.

Técnicas Utilizadas:

Kafka para decoupling

Retry com Exponential Backoff para lidar com instabilidades temporárias

Timeout explícito + fallback para respostas mockadas (modo demo)

Notificação por webhook para serviços internos

Armazenamento do status em cache (Redis)

Trade-offs:

Decisão	Prós	Contras
Tornar o fluxo assíncrono	Escalável e resiliente	Complexidade na rastreabilidade
Retry + timeout	Tolerante a falhas temporárias	Risco de acúmulo de tarefas pendentes
Uso de cache e status	Resposta rápida ao usuário	Risco de stale data se não bem controlado

Aprendizados:

Evite confiar em serviços de terceiros sem fallback ou estratégia de resiliência.

Fluxos assíncronos são mais escaláveis, mas exigem bons mecanismos de rastreamento e observabilidade (usamos Jaeger e Prometheus).

A arquitetura orientada a eventos é poderosa, desde que você modele corretamente os estados de transição e garanta idempotência.