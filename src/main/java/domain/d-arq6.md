https://docs.google.com/document/d/1yjeRtoWyFS1nQNKeLorQbfBu4-k_wFjGuSDxQk62ffI/edit?tab=t.0

CAMADA DE DOMÍNIO (CORE BUSINESS)
Definição
É o coração do sistema - contém a lógica de negócio pura, regras empresariais e entidades fundamentais. Não possui dependências externas.
Responsabilidades
Modelar o negócio com entidades e value objects
Implementar regras de negócio complexas
Validar invariantes e consistência do domínio
Definir a "verdade única" do sistema
Componentes Típicos

// ✅ PERMITIDO na camada de domínio:
Entities         // Entidades com identidade
Value Objects    // Objetos imutáveis de valor
Domain Services  // Serviços com lógica de domínio
Domain Events    // Eventos significativos para o negócio
Enums            // Enumerações do domínio
Exceptions      // Exceções específicas do domínio
Interfaces      // Interfaces de repositório e serviços (PORTAS)

// ❌ NÃO PERMITIDO:
Annotations de framework
Conexões com banco de dados
Chamadas HTTP externas
Configurações de infraestrutura
Características
Framework-agnostic: Não conhece Spring, JPA, etc.
Testável isoladamente: Testes de unidade puros
Estável: Mudanças pouco frequentes
Reutilizável: Pode ser usado em diferentes contexts
