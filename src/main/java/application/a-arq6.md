https://docs.google.com/document/d/1yjeRtoWyFS1nQNKeLorQbfBu4-k_wFjGuSDxQk62ffI/edit?tab=t.0

CAMADA DE APLICAÇÃO
Definição
Coordena as operações do sistema - orquestra o fluxo de trabalho entre o domínio e o mundo externo. Implementa os casos de uso.
Responsabilidades
Implementar casos de uso específicos
Orquestrar chamadas entre domínio e infraestrutura
Gerenciar transações (quando aplicável)
Converter entre objetos de domínio e DTOs
Aplicar autorização e validação simples
Componentes Típicos
java
// ✅ PERMITIDO na camada de aplicação:
Use Cases        // Casos de uso (Application Services)
Commands         // Comandos para alterar estado
Queries          // Consultas para ler dados
DTOs             // Objetos de transferência de dados
Ports (Interfaces) // Interfaces que definem contratos
Mappers          // Conversores entre objetos

// ❌ NÃO PERMITIDO:
Lógica de negócio complexa
Acesso direto a banco de dados
Detalhes de implementação de infraestrutura
Características
Thin layer: Contém pouca ou nenhuma lógica de negócio
Use-case oriented: Organizada em torno de funcionalidades
Transaction boundary: Geralmente define o escopo transacional
Adapter mediation: Conecta portas com adaptadores
