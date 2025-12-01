quero entender os caminhos de uma classe no domain até o controller pela arquitetura hexagonal

[REQUEST] → Controller → Service → Repository → Database
[RESPONSE] ← Controller ← Service ← Repository ← Database

1. ESTRUTURA DE PACOTES
   text
   src/
   ├── main/
   │   ├── java/
   │   │   └── com/
   │   │       └── minhaapp/
   │   │           ├── domain/                      # NÚCLEO DO NEGÓCIO
   │   │           │   ├── model/                   # Entidades de Domínio
   │   │           │   │   └── User.java
   │   │           │   └── port/                    # Interfaces (Ports)
   │   │           │       └── UserRepository.java
   │   │           ├── application/                 # LÓGICA DE APLICAÇÃO
   │   │           │   ├── service/                 # Services/Use Cases
   │   │           │   │   └── UserService.java
   │   │           │   └── dto/                     # DTOs de entrada/saída
   │   │           │       └── UserResponse.java
   │   │           └── infrastructure/              # INFRAESTRUTURA
   │   │               ├── persistence/             # Implementação Persistência
   │   │               │   ├── entity/
   │   │               │   │   └── UserEntity.java
   │   │               │   ├── repository/
   │   │               │   │   └── UserRepositoryImpl.java
   │   │               │   └── mapper/
   │   │               │       └── UserMapper.java
   │   │               └── rest/                    # Controladores Web
   │   │                   └── UserResource.java
   │   └── resources/
   │       └── application.properties


https://chat.deepseek.com/a/chat/s/ce3a0501-b5a3-4fbd-9119-9448062c0920