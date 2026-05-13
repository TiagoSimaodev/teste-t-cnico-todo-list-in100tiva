# To-Do List 100tiva - Backend API

Backend completo e profissional para uma aplicação To-Do List desenvolvido com Java 21 e Spring Boot. Esta API REST fornece operações CRUD completas para gerenciamento de tarefas, com persistência em banco de dados H2 e documentação Swagger/OpenAPI.

##  Deploy da Aplicação

- Frontend: https://to-do-lista-in100tiva.vercel.app/
- Backend API: https://teste-t-cnico-todo-list-in100tiva-production.up.railway.app/api/tasks
- Swagger UI: https://teste-t-cnico-todo-list-in100tiva-production.up.railway.app/api/swagger-ui/index.html


## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework para desenvolvimento de aplicações Java
- **Spring Web** - Para criação de APIs REST
- **Spring Data JPA** - Para persistência de dados
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **Bean Validation** - Para validação de dados
- **Swagger/OpenAPI** - Para documentação da API
- **Maven** - Gerenciamento de dependências e build

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/todolist/
│   │       ├── TodoList100TivaApplication.java    # Classe principal
│   │       ├── controller/
│   │       │   └── TaskController.java            # Endpoints REST
│   │       ├── service/
│   │       │   └── TaskService.java               # Lógica de negócio
│   │       ├── repository/
│   │       │   └── TaskRepository.java            # Acesso a dados
│   │       ├── entity/
│   │       │   └── Task.java                      # Entidade JPA
│   │       ├── dto/
│   │       │   ├── TaskRequest.java               # DTO para requests
│   │       │   └── TaskResponse.java              # DTO para responses
│   │       ├── exception/
│   │       │   ├── TaskNotFoundException.java     # Exceção customizada
│   │       │   └── GlobalExceptionHandler.java    # Tratamento global de erros
│   │       └── config/
│   │           └── CorsConfig.java                # Configuração CORS
│   └── resources/
│       └── application.properties                 # Configurações da aplicação
└── test/                                          # (Diretório para testes futuros)
```

## � Frontend

O projeto inclui um frontend completo desenvolvido com HTML5, CSS3 e JavaScript puro (Vanilla JS), sem frameworks externos.

### Tecnologias do Frontend
- **HTML5** - Estrutura da página
- **CSS3** - Estilização moderna e responsiva
- **JavaScript puro** - Lógica e consumo da API
- **Fetch API** - Requisições HTTP assíncronas

### Estrutura do Frontend
```
frontend/
├── index.html    # Estrutura da página
├── style.css     # Estilos modernos e responsivos
└── script.js     # Lógica JavaScript
```

### Funcionalidades do Frontend
- ✅ Listar todas as tarefas
- ✅ Criar novas tarefas
- ✅ Editar tarefas existentes
- ✅ Excluir tarefas
- ✅ Marcar/desmarcar como concluída
- ✅ Interface responsiva
- ✅ Feedback visual ao usuário
- ✅ Tratamento de erros
- ✅ Atualização em tempo real

### Como Executar o Frontend

1. **Certifique-se que o backend está rodando:**
   ```bash
   mvn spring-boot:run
   ```

2. **Abra o frontend no navegador:**
   - Navegue até a pasta `frontend/`
   - Abra o arquivo `index.html` diretamente no navegador
   - Ou use um servidor local simples (opcional)

3. **URLs importantes:**
   - Frontend: `file:///caminho/para/frontend/index.html`
   - Backend API: `http://localhost:8080/api/tasks`
   - Swagger UI: `http://localhost:8080/api/swagger-ui/index.html`

### Design do Frontend
- **Interface moderna** com gradiente de fundo
- **Cards para tarefas** com hover effects
- **Formulários intuitivos** para adicionar/editar
- **Modal para edição** de tarefas
- **Estados visuais** para tarefas concluídas
- **Responsivo** para mobile e desktop
- **Feedback visual** com toasts de erro
- **Animações suaves** para melhor UX

## �🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

### **Controller Layer**
- Responsável por receber as requisições HTTP
- Valida os dados de entrada com `@Valid`
- Retorna `ResponseEntity` com status HTTP apropriados
- Documenta os endpoints com Swagger

### **Service Layer**
- Contém a lógica de negócio da aplicação
- Coordena as operações entre controller e repository
- Realiza validações de negócio
- Converte entre entidades e DTOs

### **Repository Layer**
- Interface que estende `JpaRepository`
- Fornece operações CRUD básicas automaticamente
- Pode ser estendida com consultas customizadas

### **Entity Layer**
- Representa as tabelas do banco de dados
- Anotações JPA para mapeamento objeto-relacional
- Validações Bean Validation nos campos

### **DTO Layer**
- `TaskRequest`: Para dados de entrada (criação/atualização)
- `TaskResponse`: Para dados de saída
- Separação clara entre entrada e saída

### **Exception Layer**
- `TaskNotFoundException`: Exceção customizada
- `GlobalExceptionHandler`: Tratamento centralizado de erros
- Retorna respostas padronizadas com mensagens amigáveis

### **Config Layer**
- Configurações específicas da aplicação
- CORS, segurança, etc.

## 🗄️ Modelo de Dados

### Entidade Task
```java
{
    "id": Long,           // Identificador único (gerado automaticamente)
    "title": String,      // Título obrigatório (máx. 100 caracteres)
    "description": String, // Descrição opcional (máx. 500 caracteres)
    "completed": Boolean   // Status de conclusão (padrão: false)
}
```

## 🔗 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/tasks` | Criar nova tarefa |
| GET | `/api/tasks` | Listar todas as tarefas |
| GET | `/api/tasks/{id}` | Buscar tarefa por ID |
| PUT | `/api/tasks/{id}` | Atualizar tarefa |
| DELETE | `/api/tasks/{id}` | Excluir tarefa |

**Base URL:** `http://localhost:8080/api`

## 📋 Exemplos de Uso

### Criar Tarefa
```bash
POST /api/tasks
Content-Type: application/json

{
    "title": "Estudar Spring Boot",
    "description": "Aprender conceitos avançados do Spring Boot",
    "completed": false
}
```

**Resposta (201 Created):**
```json
{
    "id": 1,
    "title": "Estudar Spring Boot",
    "description": "Aprender conceitos avançados do Spring Boot",
    "completed": false
}
```

### Listar Tarefas
```bash
GET /api/tasks
```

**Resposta (200 OK):**
```json
[
    {
        "id": 1,
        "title": "Estudar Spring Boot",
        "description": "Aprender conceitos avançados do Spring Boot",
        "completed": false
    }
]
```

### Buscar Tarefa por ID
```bash
GET /api/tasks/1
```

**Resposta (200 OK):**
```json
{
    "id": 1,
    "title": "Estudar Spring Boot",
    "description": "Aprender conceitos avançados do Spring Boot",
    "completed": false
}
```

### Atualizar Tarefa
```bash
PUT /api/tasks/1
Content-Type: application/json

{
    "title": "Estudar Spring Boot Avançado",
    "description": "Aprender conceitos avançados do Spring Boot",
    "completed": true
}
```

**Resposta (200 OK):**
```json
{
    "id": 1,
    "title": "Estudar Spring Boot Avançado",
    "description": "Aprender conceitos avançados do Spring Boot",
    "completed": true
}
```

### Excluir Tarefa
```bash
DELETE /api/tasks/1
```

**Resposta (204 No Content):**

## ❌ Tratamento de Erros

### Validação de Dados (400 Bad Request)
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Erro de validação nos dados enviados",
    "fieldErrors": {
        "title": "O título é obrigatório"
    }
}
```

### Recurso Não Encontrado (404 Not Found)
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 404,
    "error": "Not Found",
    "message": "Tarefa não encontrada com ID: 999"
}
```

### Erro Interno (500 Internal Server Error)
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Ocorreu um erro interno no servidor"
}
```

## 🛠️ Como Executar o Projeto

### Pré-requisitos
- Java 21 instalado
- Maven 3.6+ instalado
- Navegador web moderno

### Passos para Execução

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd to-do-list-100tiva
   ```

2. **Compile e execute o backend:**
   ```bash
   mvn clean compile
   mvn spring-boot:run
   ```

3. **Abra o frontend:**
   - Navegue até a pasta `frontend/`
   - Abra o arquivo `index.html` no navegador
   - Ou use um servidor local: `python -m http.server 8000` (opcional)

4. **Verifique se está rodando:**
   - **Frontend:** Abra `frontend/index.html` no navegador
   - **API:** `http://localhost:8080/api/tasks`
   - **Swagger UI:** `http://localhost:8080/api/swagger-ui/index.html`
   - **H2 Console:** `http://localhost:8080/api/h2-console`

### Testando a Aplicação

Você pode testar a aplicação completa usando:
- **Interface web** (recomendado) - Abra `frontend/index.html`
- **Postman** ou **Insomnia** para testar apenas a API
- **curl** no terminal
- **Swagger UI** para documentação interativa

#### Exemplo com curl:
```bash
# Criar tarefa
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Minha primeira tarefa", "description": "Descrição da tarefa", "completed": false}'

# Listar tarefas
curl -X GET http://localhost:8080/api/tasks

# Buscar tarefa por ID
curl -X GET http://localhost:8080/api/tasks/1

# Atualizar tarefa
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "Tarefa atualizada", "completed": true}'

# Excluir tarefa
curl -X DELETE http://localhost:8080/api/tasks/1
```

## 🗃️ Banco de Dados H2

### Acesso ao H2 Console
- **URL:** `http://localhost:8080/api/h2-console`
- **JDBC URL:** `jdbc:h2:mem:todolistdb`
- **Username:** `sa`
- **Password:** *(vazio)*

### Configurações do H2
- **Banco em memória:** Os dados são perdidos ao reiniciar a aplicação
- **DDL Auto:** `create-drop` (recria as tabelas a cada inicialização)
- **Console habilitado:** Para facilitar o desenvolvimento

## Documentação da API (Swagger)

A documentação completa da API está disponível via Swagger UI:
- **URL:** `http://localhost:8080/api/swagger-ui.html`

O Swagger fornece:
- Lista completa de endpoints
- Especificações OpenAPI 3.0
- Interface interativa para testar os endpoints
- Exemplos de requests e responses

## 🔄 Migração para PostgreSQL

Para migrar de H2 para PostgreSQL em produção:

1. **Adicione a dependência do PostgreSQL no pom.xml:**
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

2. **Atualize o application.properties:**
   ```properties
   # Configurações do Banco PostgreSQL
   spring.datasource.url=jdbc:postgresql://localhost:5432/todolistdb
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.datasource.driver-class-name=org.postgresql.Driver

   # Configurações do JPA
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Configure variáveis de ambiente** para credenciais sensíveis.

##  Dependências Maven Explicadas

### spring-boot-starter-web
- Fornece tudo necessário para criar aplicações web com Spring MVC
- Inclui Tomcat embedded, Jackson para JSON, etc.

### spring-boot-starter-data-jpa
- Integração com JPA/Hibernate
- Repositórios, transações, etc.

### h2
- Banco de dados em memória para desenvolvimento
- Console web para visualizar dados

### spring-boot-starter-validation
- Implementação de Bean Validation (Hibernate Validator)
- Validações automáticas com @Valid

### springdoc-openapi-starter-webmvc-ui
- Geração automática de documentação OpenAPI 3.0
- Interface Swagger UI

##  Boas Práticas Implementadas

- **Separação de responsabilidades:** Cada camada tem sua função específica
- **DTOs:** Separação entre dados de entrada e saída
- **Validação:** Bean Validation em DTOs e entidades
- **Tratamento de erros:** Centralizado e padronizado
- **CORS configurado:** Para integração com frontends
- **Documentação:** Swagger para facilitar testes e integração
- **Logs:** Configurados para debug durante desenvolvimento
- **Nomes descritivos:** Classes, métodos e variáveis bem nomeados
- **Comentários:** Código documentado com JavaDoc

##  Segurança

Esta versão não inclui autenticação/autorização para manter a simplicidade. Para produção, considere adicionar:
- Spring Security
- JWT tokens
- OAuth2
- Validação de entrada mais rigorosa

##  Testes

Testes automatizados não foram implementados neste projeto para manter o foco no CRUD básico. Para um projeto completo, recomenda-se:
- Testes unitários com JUnit 5 e Mockito
- Testes de integração com TestContainers
- Testes end-to-end com REST Assured

##  Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request
s
