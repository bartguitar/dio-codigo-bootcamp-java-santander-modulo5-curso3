# 🔐 DIO Projeto Módulo 5 · Curso 3 — Spring Security

Projeto desenvolvido como parte do **Bootcamp Java da DIO em parceria com o Santander**, no módulo 5 (curso 3), com foco em **Spring Security**: autenticação via API REST, autorização baseada em papéis (roles) e controle de acesso a dados por escopo (próprio vs. todos), aplicado a um domínio de **propostas de parceria entre influenciadores e marcas**.

## 📋 Sobre o projeto

A aplicação simula uma plataforma onde:
- **Influenciadores** (`ROLE_INFLUENCER`) criam propostas de parceria e só podem visualizar as **próprias** propostas;
- **Marcas** (`ROLE_BRAND`) podem visualizar **todas** as propostas criadas.

O projeto evoluiu em etapas (documentadas em [`docs/rascunho_tutorial.md`](docs/rascunho_tutorial.md)), partindo de uma configuração mínima do Spring Boot até chegar a um fluxo completo de autenticação customizada, autorização por método e persistência com JPA/MySQL.

## 🚀 Tecnologias

- **Java 25**
- **Spring Boot 4.1.0**
- **Spring Security** (autenticação e autorização)
- **Spring Data JPA**
- **MySQL 9.6** (via Docker Compose)
- **Lombok** (plugin `io.freefair.lombok`)
- **Gradle** (com wrapper)
- **Docker Compose** (integração automática via `spring-boot-docker-compose`)

## 🏗️ Arquitetura

O código é organizado em **módulos de domínio** (`auth` e `proposal`), cada um dividido nas camadas:

```
src/main/java/br/com/dio/dioprojetomodulo5curso3springsecurity/
├── auth/
│   ├── domain/                      # Enum de papéis (UserRole)
│   └── infrastructure/
│       ├── http/
│       │   ├── Controller.java              # Endpoints de teste (/, /influencer, /brand)
│       │   └── security/
│       │       ├── SecurityConfig.java                       # Configuração central do Spring Security
│       │       ├── RestUsernamePasswordAuthenticationFilter.java  # Login via JSON (POST)
│       │       └── JpaUserDetailsService.java                # Carrega usuários do banco
│       └── persistence/
│           ├── entity/User.java             # Entidade JPA que implementa UserDetails
│           └── repository/UserRepository.java
│
└── proposal/
├── domain/                      # Proposal, Owner, ProposalId, OwnerId, ProposalRepository (porta)
├── application/
│   ├── CreateProposalUseCase.java
│   ├── ListProposalsUseCase.java
│   ├── input/CreateProposalInput.java
│   ├── output/ProposalOutput.java
│   └── list/                    # Strategy Pattern para escopo de listagem
│       ├── AccessScope.java             # OWN | ALL
│       ├── Strategy.java
│       ├── OwnStrategy.java             # Retorna apenas propostas do próprio usuário
│       ├── AllStrategy.java             # Retorna todas as propostas
│       └── Factory.java                 # Seleciona a estratégia conforme o papel do usuário
└── infrastructure/
├── http/
│   ├── ProposalController.java
│   ├── request/CreateProposalRequest.java
│   └── response/ProposalResponse.java
└── persistence/
├── entity/ProposalEntity.java
└── repository/
├── ProposalEntityRepository.java    # Spring Data JPA
└── JpaProposalRepository.java       # Implementação da porta ProposalRepository
```

- A separação em `domain` / `application` / `infrastructure` segue princípios de **arquitetura hexagonal / DDD leve**: o domínio (`Proposal`, `Owner`, etc.) não conhece detalhes de persistência ou HTTP, e a camada de aplicação orquestra casos de uso através de interfaces (portas) implementadas na infraestrutura.

## 🔑 Segurança

### Autenticação customizada
Em vez do `formLogin` padrão do Spring Security, o projeto implementa um filtro próprio (`RestUsernamePasswordAuthenticationFilter`) que:
- Aceita login via **POST `/api/auth/login`** com corpo JSON `{ "username": "...", "password": "..." }`;
- Retorna **200 OK** em caso de sucesso, mantendo a sessão autenticada;
- Utiliza `BCryptPasswordEncoder` para hash de senhas.

### Autorização baseada em papéis
- `@EnableMethodSecurity` + `@PreAuthorize` protegem endpoints por papel (`hasRole(...)`, `hasAnyRole(...)`);
- Papéis disponíveis: `ROLE_INFLUENCER` e `ROLE_BRAND`.

### Controle de acesso por escopo (Strategy Pattern)
Ao listar propostas, o sistema decide dinamicamente qual estratégia aplicar conforme o papel do usuário autenticado:

| Papel | Escopo (`AccessScope`) | Estratégia | Resultado |
|---|---|---|---|
| `ROLE_INFLUENCER` | `OWN` | `OwnStrategy` | Apenas as propostas do próprio usuário |
| `ROLE_BRAND` | `ALL` | `AllStrategy` | Todas as propostas cadastradas |

A `Factory` seleciona automaticamente a `Strategy` correta com base no `AccessScope`, evitando `if/else` espalhados pelo código.

## 📡 Endpoints

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/api/auth/login` | Público | Autentica o usuário via JSON (`username`, `password`) |
| `GET` | `/` | Autenticado | Retorna `Hello World {id}` do usuário logado |
| `GET` | `/influencer` | `ROLE_INFLUENCER` | Endpoint de teste exclusivo para influenciadores |
| `GET` | `/brand` | `ROLE_BRAND` | Endpoint de teste exclusivo para marcas |
| `POST` | `/proposals` | `ROLE_INFLUENCER` | Cria uma nova proposta |
| `GET` | `/proposals` | `ROLE_INFLUENCER` ou `ROLE_BRAND` | Lista propostas (escopo conforme o papel) |

## 🗄️ Banco de dados

O `compose.yml` sobe automaticamente um container **MySQL 9.6** ao iniciar a aplicação (via `spring-boot-docker-compose`):

```yaml
services:
  database:
    image: mysql:9.6
    environment:
      MYSQL_DATABASE: proposals
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: app
      MYSQL_PASSWORD: app
    ports:
      - "3307:3306"
```

> ⚠️ `spring.jpa.hibernate.ddl-auto=create` está configurado, ou seja, o schema é **recriado a cada inicialização** da aplicação. Ideal para fins de estudo, mas não deve ser usado em produção.

### Usuários pré-carregados

Ao subir a aplicação pela primeira vez, um `CommandLineRunner` popula o banco com 3 usuários de teste (senha para todos: `password`):

| Username | Papel |
|---|---|
| `fitness_vibe` | `ROLE_INFLUENCER` |
| `tech_guru` | `ROLE_INFLUENCER` |
| `logistics` | `ROLE_BRAND` |

## ▶️ Como executar

### Pré-requisitos
- Java 25
- Docker e Docker Compose (para o banco de dados MySQL)

### Passos

```bash
# Clonar o repositório
git clone https://github.com/bartguitar/dio-codigo-bootcamp-java-santander-modulo5-curso3.git
cd dio-codigo-bootcamp-java-santander-modulo5-curso3

# Rodar a aplicação (o Docker Compose do MySQL sobe automaticamente)
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`.

### Testando com cURL

```bash
# 1. Login
curl -i -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -c cookies.txt \
  -d '{"username":"fitness_vibe","password":"password"}'

# 2. Criar uma proposta (usando a sessão autenticada)
curl -X POST http://localhost:8080/proposals \
  -H "Content-Type: application/json" \
  -b cookies.txt \
  -d '{"title":"Parceria de divulgação","description":"Campanha de 3 posts"}'

# 3. Listar propostas (retorna apenas as do influenciador logado)
curl -X GET http://localhost:8080/proposals -b cookies.txt
```

## 🧭 Trilha de aprendizado

O desenvolvimento foi guiado por etapas incrementais, cada uma finalizada com um commit:

1. **Configuração inicial** do projeto Spring Boot;
2. **Primeiros passos com Spring Security** — configuração básica, `PasswordEncoder`, endpoints protegidos por método (`@PreAuthorize`);
3. **Evolução da autenticação** — filtro customizado de login via JSON substituindo o `formLogin`;
4. **Segurança com banco de dados** — MySQL via Docker Compose, entidade `User` implementando `UserDetails`, `JpaUserDetailsService`;
5. **Segurança baseada em papéis** — módulo `proposal` com domínio, casos de uso e repositórios;
6. **Listagem com Strategy Pattern** — escopo de acesso (`OWN`/`ALL`) conforme o papel do usuário;
7. **Entidades de persistência** — mapeamento JPA e adaptação domínio ↔ entidade;
8. **`ProposalController`** — exposição HTTP dos casos de uso de criação e listagem.

Confira o detalhamento completo de cada passo em [`docs/rascunho_tutorial.md`](docs/rascunho_tutorial.md).

## 📄 Licença

Projeto de estudo desenvolvido para fins educacionais no Bootcamp DIO/Santander.

