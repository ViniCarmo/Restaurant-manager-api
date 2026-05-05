# Restaurant Manager API

API REST para gerenciamento de usuários em um sistema compartilhado de restaurantes, desenvolvida com Java 21 e Spring Boot 3.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Flyway
- Docker / Docker Compose
- SpringDoc / Swagger UI
- JUnit 5 + Mockito

## Funcionalidades

- Cadastro, atualização e exclusão de clientes e proprietários de restaurante
- Endpoint exclusivo para troca de senha
- Busca de usuários por nome (parcial, case-insensitive)
- Autenticação via login ou email com retorno de token JWT
- Tratamento de erros padronizado via RFC 7807 (ProblemDetail)
- Documentação interativa via Swagger UI
- Testes unitários com JUnit 5 e Mockito

## Como executar

### Pré-requisitos

- Docker Desktop instalado e em execução

### Subindo a aplicação

```bash
git clone https://github.com/ViniCarmo/Restaurant-manager-api
cd Restaurant-manager-api
docker-compose up --build
```

A aplicação estará disponível em `http://localhost:8080`.

### Encerrando os containers

```bash
docker-compose down
```

## Documentação

Acesse o Swagger UI em:

```
http://localhost:8080/swagger-ui/index.html
```

Para acessar os endpoints protegidos:
1. Faça login em `POST /api/v1/auth` com seu login ou email e senha
2. Copie o token retornado
3. Clique em **Authorize** no Swagger e informe o token

## Endpoints

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | /api/v1/auth | Login — retorna token JWT | Não |
| POST | /api/v1/customers | Criar cliente | Não |
| GET | /api/v1/customers/{id} | Buscar cliente por ID | Sim |
| GET | /api/v1/customers/name?name= | Buscar clientes por nome | Sim |
| PUT | /api/v1/customers/{id} | Atualizar dados do cliente | Sim |
| PUT | /api/v1/customers/{id}/password | Atualizar senha do cliente | Sim |
| DELETE | /api/v1/customers/{id} | Deletar cliente | Sim |
| POST | /api/v1/restaurant-owners | Criar proprietário | Não |
| GET | /api/v1/restaurant-owners/{id} | Buscar proprietário por ID | Sim |
| GET | /api/v1/restaurant-owners/name?name= | Buscar proprietários por nome | Sim |
| PUT | /api/v1/restaurant-owners/{id} | Atualizar dados do proprietário | Sim |
| PUT | /api/v1/restaurant-owners/{id}/password | Atualizar senha do proprietário | Sim |
| DELETE | /api/v1/restaurant-owners/{id} | Deletar proprietário | Sim |

## Variáveis de ambiente

| Variável | Descrição | Valor padrão |
|----------|-----------|--------------|
| SPRING_DATASOURCE_URL | URL de conexão com o banco | jdbc:postgresql://db:5432/restaurant_management_db |
| SPRING_DATASOURCE_USERNAME | Usuário do banco | postgres |
| SPRING_DATASOURCE_PASSWORD | Senha do banco | admin |
| JWT_secret_key | Chave secreta para geração do token JWT | 12345678 |

## Coleção Postman

A coleção com todos os cenários de teste está disponível em `postman/collections/RestaurantManagement.postman_collection.json`.

Importe o arquivo no Postman para testar todos os endpoints, incluindo cenários de erro.

## Estrutura do projeto

```
src/
├── main/
│   ├── java/dev/vinicius/restaurant_management_api/
│   │   ├── controllers/
│   │   ├── dto/
│   │   ├── entities/
│   │   ├── infra/
│   │   │   ├── exception/
│   │   │   ├── security/
│   │   │   └── springdoc/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/dev/vinicius/restaurant_management_api/
        └── service/
postman/
├── collections/
└── globals/
```

## Autor

Vinicius Oliveira do Carmo — RM370572
Pós Tech 12ADJT — FIAP
