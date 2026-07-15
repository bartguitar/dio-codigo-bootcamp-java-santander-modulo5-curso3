#### Tecnologias
- Java 25
- Spring 4.1.0
- Spring Security

### Seção 1 - Introdução ao simplificando a segurnça
#### Configuração de Dependência inicial
- Não foi adicionada nenhuma dependência inicial para o projeto, somente as dependências padrões, 
- irei adicionando assim que o projeto for evoluindo.

```
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.1.0'
    id 'io.spring.dependency-management' version '1.1.7'
}

group = 'br.com.dio'
version = '0.0.1-SNAPSHOT'
description = 'dio-projeto-modulo5-curso3-spring-security'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}
```
### Seção 2 - Primeiros Passos com Spring Security
2.1 - Adicionar dependência "spring-security" e a "starter-web" \
2.2 - Criar pacote "auth" com as 3 pastas dentro "application/domain/infra..." \
2.3 - Criar classe "SecurityConfig" dentro de "infra..." \
2.4 - Rodar e testar aplicação em http://localhost:8080/login \
2.5 - Adicionar "@bean/PasswordEncoder" em "SecurityConfig" \
2.6 - Criar pasta "http" classe "Controller" \
2.7 - Testar a aplicação em http://localhost:8080/ pra ver se o controller está funcionando \
2.8 - Adicionar anotação "@AuthenticationPrincipal" \
2.9 - testar aplicação na url porta 8080 \
2.10 - Adicionar anotação "@EnableMethodSecurity" \
2.11 - Adicionar no "controller" metodos "influencerEndpoint" e "brandEndpoint" \
2.12 - Testar aplicação em "localhost/8080/influencer" e "localhost/8080/brand" \
--Feito Commit--
### Seção 3 - Evoluindo a Autenticação
3.1 - Alterar classe "security" adicionar ".csrf" \
3.2 - Construir pacote "security" \
3.3 - Criar classe "RestUsernamePasswordAuthenticationFilter" \
3.4 - Testar aplicação \
3.5 - Alterar classe "SecurityConfig" \
3.6 - Deixar em comentário o "formLogin" \
3.7 - Criar metodo "addFilterAt" \
3.8 - Fazer POST e testar aplicação em http://localhost:8080 \
--Feito Commit--
### Segurança com Banco de Dados
4.1 - Criar arquivo "compose.yml" subindo o banco de dados mysql \
4.2 - Adicionar 3 dependências "docker-compose" / "starter-data-jpa" / "connector-j" \
4.3 - Adicionar Plugin "lombok" \
4.4 - Testar aplicação se subiu o container do mysql \
4.5 - Testar se criou o banco de dados \
4.6 - Adicionar configurações do banco de dados em "application.properties" \
--Feito Commit-- \
4.7 - Criar primeira entidade pacote "persistence/entity" entidade "User" \
4.8 - Criar classe enum "UserRole" dentro de "domain" \
4.9 - Testar Aplicação \
4.10 - Criar pacote "repository" e classe interface "UserRepository" \
4.11 - Criar classe "JpaUserDetailsService" \
4.12 - Excluir método "userDetailsService" da classe "SecurityConfig" \
4.13 - Criar método "initDatabase" \
4.14 - Subir aplicação e testar POST/GET \
4.15 - Alterar classe "Controller" no método "hello" \
4.16 - Subir aplicação e testar POST/GET \
4.17 - Alterar classe "UserRole" colocando o prefixo antes do nome para padronizar para o spring \
4.18 - Alterar classe "SecurityConfig" para os novos nomes criados no item anterior \
--Feito Commit--
### Seção 5 - Segurança Baseada em Papéis
5.1 - Criar o módulo "proposal" e pacotes "application"/"domain"/"infrastructure" \
5.2 - Criar classe de domínio "Proposal" e classe record "ProposalId" \
5.3 - Criar classe record "Owner" e classe record "OwnerId" \
5.4 - Criar classe interface "ProposalRepository" \
5.5 - Criar classe "CreateProposalUseCase" \
5.6 - Criar pacote "input" e classe record "CreateProposalInput" \
5.7 - Criar método "toDomain" \
5.8 - Criar pacote "output" e classe record "ProposalOutput" \
--Feito Commit--
### Seção 6 - Implementando o Use Case de Listagem
6.1 - Criar classe "ListProposalsUseCase" \
6.2 - Criar pacote "list" \
6.3 - Criar classe enum "AccessScope" \
6.4 - Criar classe interface "Strategy" \
6.5 - Criar classe "OwnStrategy"/"AllStrategy"/"Factory" \
6.6 - Terminar de implementar classe "ListProposalsUseCase" \
--Feito Commit--
### Seção 7 - Criando Entidades de Persistência
7.1 - Criar pacote "infra.../persistence" e pacotes "entity"/"repository" \
7.2 - Criar classe "ProposalEntity" \
7.3 - Criar classe interface "ProposalEntityRepository" \
7.4 - Criar classe "JpaProposalRepository" \
--Feito Commit--