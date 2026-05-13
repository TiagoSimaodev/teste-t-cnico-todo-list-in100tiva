# 🗄️ Configuração de Banco de Dados

## 📋 Visão Geral

O projeto suporta múltiplos bancos de dados através de **Spring Profiles**:

### Perfis Disponíveis

| Perfil | Banco | Ambiente | Arquivo |
|--------|-------|----------|---------|
| `default` | H2 (Em memória) | Desenvolvimento | `application.properties` |
| `postgresql` | PostgreSQL Local | Desenvolvimento | `application-postgresql.properties` |
| `prod` | PostgreSQL (RDS AWS) | Produção | `application-prod.properties` |

---

## 🚀 Como Usar Cada Perfil

### 1️⃣ **Desenvolvimento com H2 (Padrão)**

O H2 é configurado por padrão. Nenhuma alteração necessária.

```bash
# Apenas execute normalmente
mvn spring-boot:run
```

**Características:**
- Banco em memória (dados perdidos ao reiniciar)
- Sem instalação necessária
- Console disponível em: `http://localhost:8080/api/h2-console`
- Ideal para testes rápidos

---

### 2️⃣ **Desenvolvimento com PostgreSQL Local**

Se deseja usar PostgreSQL em sua máquina local:

#### Instalação do PostgreSQL

**Windows:**
```bash
# Baixe e instale de: https://www.postgresql.org/download/windows/
# Ou use Chocolatey:
choco install postgresql
```

**macOS:**
```bash
# Usando Homebrew:
brew install postgresql@15
brew services start postgresql@15
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get install postgresql postgresql-contrib
sudo systemctl start postgresql
```

#### Criação do Banco

```bash
# Acesse o PostgreSQL
psql -U postgres

# No prompt do PostgreSQL, execute:
CREATE DATABASE todolist_db;
\q
```

#### Executar com PostgreSQL Local

```bash
# Via variável de ambiente
export SPRING_PROFILES_ACTIVE=postgresql
mvn spring-boot:run

# Ou via command line
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=postgresql"

# Ou no Windows PowerShell
$env:SPRING_PROFILES_ACTIVE='postgresql'; mvn spring-boot:run
```

---

### 3️⃣ **Produção com AWS RDS PostgreSQL**

#### Passo 1: Criar RDS no AWS

1. Acesse [AWS Console](https://console.aws.amazon.com)
2. Navegue até **RDS** → **Databases**
3. Clique em **Create Database**
4. Configure:
   - **Engine:** PostgreSQL (versão 14+)
   - **DB instance identifier:** `todolist-db`
   - **Master username:** `admin`
   - **Master password:** Escolha uma senha forte
   - **DB instance class:** `db.t3.micro` (gratuito ou barato)
   - **Storage:** 20 GB
   - **Public accessibility:** Sim (para testes)
   - **Database name:** `todolist_db`
5. Clique em **Create database**

#### Passo 2: Configurar Security Group

1. Na página da instância RDS, anote o **Endpoint** (ex: `todolist-db.xxxxx.us-east-1.rds.amazonaws.com`)
2. Vá em **Security groups** → Selecione o grupo da RDS
3. Adicione uma **Inbound rule:**
   - Type: PostgreSQL
   - Protocol: TCP
   - Port: 5432
   - Source: Seu IP ou 0.0.0.0/0 (menos seguro)

#### Passo 3: Configurar Credenciais no application-prod.properties

```properties
spring.datasource.url=jdbc:postgresql://todolist-db.xxxxx.us-east-1.rds.amazonaws.com:5432/todolist_db
spring.datasource.username=admin
spring.datasource.password=SuaSenhaForte123!
```

#### Passo 4: Usar Variáveis de Ambiente (Recomendado)

Em vez de hardcodear as credenciais, use variáveis de ambiente:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/todolist_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```

#### Passo 5: Executar em Produção

```bash
# Via variáveis de ambiente (recomendado para AWS)
export DB_URL=jdbc:postgresql://seu-endpoint.rds.amazonaws.com:5432/todolist_db
export DB_USERNAME=admin
export DB_PASSWORD=SuaSenhaForte123!
export SPRING_PROFILES_ACTIVE=prod

mvn spring-boot:run

# Ou deploy via Elastic Beanstalk/EC2
java -jar target/to-do-list-100tiva-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=prod \
  --spring.datasource.url=jdbc:postgresql://seu-endpoint:5432/todolist_db \
  --spring.datasource.username=admin \
  --spring.datasource.password=SuaSenhaForte123!
```

---

## 🔧 Configurações de Conexão Detalhadas

### H2 (Development - Padrão)

```properties
# Banco em memória
spring.datasource.url=jdbc:h2:mem:todolistdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# DDL: create-drop (recria a cada inicialização)
spring.jpa.hibernate.ddl-auto=create-drop

# Console web para visualização
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### PostgreSQL (Local & Production)

```properties
# Conexão
spring.datasource.url=jdbc:postgresql://HOST:PORT/DATABASE
spring.datasource.username=USER
spring.datasource.password=PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

# Dialect
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# DDL: update (mantém dados existentes)
spring.jpa.hibernate.ddl-auto=update

# Pool de conexões (HikariCP)
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000
```

---

## 📊 Comparação de Bancos

| Recurso | H2 | PostgreSQL Local | AWS RDS |
|---------|-----|------------------|---------|
| **Dados Persistem** | ❌ Não | ✅ Sim | ✅ Sim |
| **Instalação** | ❌ Nenhuma | ✅ Necessária | ✅ Gerenciada |
| **Performance** | ⚠️ Lenta | ✅ Rápida | ✅ Excelente |
| **Backup** | ❌ Não | ⚠️ Manual | ✅ Automático |
| **Custo** | Grátis | Grátis | Pago (~$15-30/mês) |
| **Ideal Para** | Testes rápidos | Dev local | Produção |

---

## 🔐 Segurança para Produção

### 1. Nunca hardcode credenciais no código!

❌ Errado:
```properties
spring.datasource.password=minha_senha_123
```

✅ Correto:
```properties
spring.datasource.password=${DB_PASSWORD}
```

### 2. Use AWS Secrets Manager ou Parameter Store

```java
// Na sua aplicação Spring Boot:
// Integre com AWS Secrets Manager para recuperar credenciais dinamicamente
```

### 3. Restringir acesso RDS

- Limite o acesso por IP
- Use grupos de segurança (Security Groups)
- Habilite SSL para conexão

---

## 🧪 Testando as Configurações

### Verificar Qual Perfil Está Ativo

```bash
# Adicione ao seu console/logs (o Spring Boot mostra automaticamente):
# A saída mostrará qual datasource está sendo usado
```

### Testar Conectividade

```bash
# PostgreSQL local
psql -U postgres -h localhost -d todolist_db

# AWS RDS (via terminal)
psql -U admin -h seu-endpoint.rds.amazonaws.com -d todolist_db
```

### Testar via API

```bash
# Se conectado corretamente, a API deve responder
curl -X GET http://localhost:8080/api/tasks
```

---

## 📝 Resumo de Comandos

```bash
# Desenvolvimento (H2 padrão)
mvn spring-boot:run

# Desenvolvimento (PostgreSQL local)
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=postgresql"

# Produção (AWS RDS)
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

# Build para produção
mvn clean package -DskipTests

# Executar JAR em produção
java -jar target/to-do-list-100tiva-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

---

## 🆘 Troubleshooting

### Erro: "Cannot connect to PostgreSQL"

- Verifique se PostgreSQL está rodando
- Verifique credenciais (usuário/senha)
- Verifique se o banco de dados existe
- Testar conexão manualmente com `psql`

### Erro: "Connection refused"

- RDS não está acessível
- Verifique security groups do RDS
- Verifique se seu IP está liberado
- Teste ping para o endpoint RDS

### Erro: "DDL-auto: update"

Se as tabelas não estão sendo criadas:
- Mude para `ddl-auto=create-drop` (vai deletar dados!)
- Ou crie as tabelas manualmente no PostgreSQL

---

**Arquivo mais recente:** `application-prod.properties` (Produção RDS) ✅
