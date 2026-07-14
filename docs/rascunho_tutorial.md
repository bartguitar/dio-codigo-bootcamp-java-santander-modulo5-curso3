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