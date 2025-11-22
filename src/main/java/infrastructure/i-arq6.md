
https://docs.google.com/document/d/1yjeRtoWyFS1nQNKeLorQbfBu4-k_wFjGuSDxQk62ffI/edit?tab=t.0

CAMADA DE INFRAESTRUTURA
Definição
Implementa as conexões com o mundo externo - fornece as implementações concretas das interfaces definidas nas camadas internas.
Responsabilidades
Implementar persistência (banco de dados)
Expor APIs REST/gRPC
Consumir serviços externos
Configurar frameworks
Implementar mensageria, cache, etc.
Componentes Típicos
java

// ✅ PERMITIDO na camada de infraestrutura:
Controllers      // Endpoints REST/Web
Repositories     // Implementações de persistência
API Clients      // Clientes para serviços externos
Message Listeners // Consumidores de mensagens
Configurations   // Configurações de framework
Entities JPA     // Entidades de persistência
Mappers          // Conversores para/do domínio


// ❌ NÃO PERMITIDO:
Lógica de negócio
Regras de domínio
Definições de casos de uso
Características
Framework-dependent: Usa Spring, JPA, Hibernate, etc.
Replaceable: Implementações podem ser trocadas facilmente
Technical concerns: Lida com preocupações técnicas
External communication: Comunica-se com mundo externo
