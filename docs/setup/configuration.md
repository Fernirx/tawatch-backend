# Configuration Guide

This guide explains how to configure Tawatch Backend for different environments using Spring profiles and environment variables.

## Spring Profiles

The project uses **Spring profiles** to manage environment-specific settings:

- `local` — For local development with Maven
- `docker` — For Dockerized environments (Recommended)

---

## Configuration Setup

### For Docker Environments (Recommended)

When using Docker, configuration is primarily managed through environment variables in the `.env` file.

#### Linux/macOS

1. **Create environment file**
   ```bash
   cp .env.example .env
   ```

2. **Edit configuration**
   ```bash
   # Using nano (beginner-friendly)
   nano .env
   ```

3. **View current configuration**
   ```bash
   cat .env
   ```

#### Windows

1. **Create environment file**
   ```cmd
   copy .env.example .env
   ```

2. **Edit configuration**
   ```cmd
   # Using Notepad
   notepad .env
   ```

3. **View current configuration**
   ```cmd
   type .env
   ```

### Available Environment Variables

#### Application Settings

| Variable                 | Description                           | Default Value  |
|--------------------------|---------------------------------------|----------------|
| `APP_NAME`               | Application name identifier           | `tawatch`      |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile                 | `docker`       |

#### Server Configuration

| Variable              | Description                      | Default Value  |
|-----------------------|----------------------------------|----------------|
| `HOST_PORT`           | Port exposed on host machine     | `8080`         |
| `SERVER_PORT`         | Internal application server port | `8080`         |
| `SERVER_CONTEXT_PATH` | API base context path            | `/api/tawatch` |

#### Database Configuration

| Variable                        | Description                              | Default Value      |
|---------------------------------|------------------------------------------|--------------------|
| `DB_HOST`                       | Database host                            | `mysql`            |
| `DB_PORT`                       | Database port                            | `3306`             |
| `DB_NAME`                       | Database name                            | `tawatch_db`       |
| `DB_USERNAME`                   | Database username                        | `root`             |
| `DB_PASSWORD`                   | Database password                        | `password`         |
| `DB_TIMEZONE`                   | Database timezone                        | `Asia/Ho_Chi_Minh` |
| `DB_USE_SSL`                    | Enable SSL for database connection       | `false`            |
| `DB_ALLOW_PUBLIC_KEY_RETRIEVAL` | Allow public key retrieval (MySQL auth) | `true`             |

#### JPA / Hibernate Settings

| Variable                  | Description                          | Default Value                       |
|---------------------------|--------------------------------------|-------------------------------------|
| `JPA_SHOW_SQL`            | Show SQL queries in logs             | `false`                             |
| `HIBERNATE_DIALECT`       | Hibernate SQL dialect                | `org.hibernate.dialect.MySQL8Dialect` |
| `HIBERNATE_SQL_COMMENTS`  | Add comments to generated SQL        | `true`                              |
| `HIBERNATE_BATCH_SIZE`    | JDBC batch size                      | `20`                                |
| `HIBERNATE_ORDER_INSERTS` | Optimize insert ordering             | `true`                              |
| `HIBERNATE_ORDER_UPDATES` | Optimize update ordering             | `true`                              |

#### Flyway Migration Settings

| Variable            | Description                    | Default Value            |
|---------------------|--------------------------------|--------------------------|
| `FLYWAY_LOCATIONS`  | Location of migration scripts  | `classpath:db/migration` |

#### Logging Configuration

| Variable             | Description                                  | Default Value |
|----------------------|----------------------------------------------|---------------|
| `LOG_ROOT`           | Root logger level                            | `INFO`        |
| `LOG_APP_LEVEL`      | Application-specific logging level           | `DEBUG`       |
| `LOG_SPRING_WEB`     | Spring Web logging level                     | `DEBUG`       |
| `LOG_HIBERNATE_SQL`  | Hibernate SQL logging level                  | `DEBUG`       |
| `LOG_HIBERNATE_BIND` | Hibernate parameter binding logging (TRACE shows values) | `TRACE`       |
| `LOG_FLYWAY`         | Flyway migration logging level               | `DEBUG`       |

---

## Common Configuration Changes

### Changing Application Port

#### Linux/macOS
```bash
# Edit .env file
nano .env

# Change this line:
HOST_PORT=8081
```

#### Windows
```cmd
# Edit .env file
notepad .env

# Change this line:
HOST_PORT=8081
```

### Changing Database Configuration

#### Linux/macOS
```bash
nano .env

# Change these lines:
DB_NAME=my_database
DB_USERNAME=my_user
DB_PASSWORD=my_secure_password
```

#### Windows
```cmd
notepad .env

# Change these lines:
DB_NAME=my_database
DB_USERNAME=my_user
DB_PASSWORD=my_secure_password
```

### Changing API Base Path

#### Linux/macOS
```bash
nano .env

# Change this line:
SERVER_CONTEXT_PATH=/api/myapp
```

#### Windows
```cmd
notepad .env

# Change this line:
SERVER_CONTEXT_PATH=/api/myapp
```

---

## Variable Substitution

The application configuration files support variable substitution from the `.env` file. For example:

```yaml
spring:
  application:
    name: ${APP_NAME:tawatch}

server:
  port: ${SERVER_PORT:8080}
  servlet:
    context-path: ${SERVER_CONTEXT_PATH:/api/tawatch}
    encoding:
      charset: UTF-8
      enabled: true
      force: true
```

This allows you to change settings without modifying the configuration files directly.

---

## Complete Configuration Reference

Below is the complete YAML configuration structure with all available options:

### Application & Server Configuration

```yaml
spring:
  application:
    name: ${APP_NAME:tawatch}

server:
  port: ${SERVER_PORT:8080}
  servlet:
    context-path: ${SERVER_CONTEXT_PATH:/api/tawatch}
    encoding:
      charset: UTF-8
      enabled: true
      force: true
```

### Database Configuration

```yaml
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:tawatch_db}
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:password}
    driver-class-name: com.mysql.cj.jdbc.Driver

    # Hikari Connection Pool Configuration
    hikari:
      data-source-properties:
        serverTimezone: ${DB_TIMEZONE:Asia/Ho_Chi_Minh}
        useSSL: ${DB_USE_SSL:false}
        allowPublicKeyRetrieval: ${DB_ALLOW_PUBLIC_KEY_RETRIEVAL:true}
```

### JPA / Hibernate Configuration

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: validate    # Use 'validate' with Flyway
    show-sql: ${JPA_SHOW_SQL:false}

    properties:
      hibernate:
        format_sql: true
        dialect: ${HIBERNATE_DIALECT:org.hibernate.dialect.MySQL8Dialect}
        use_sql_comments: ${HIBERNATE_SQL_COMMENTS:true}

        # JDBC Batch Processing
        jdbc:
          batch_size: ${HIBERNATE_BATCH_SIZE:20}
          order_inserts: ${HIBERNATE_ORDER_INSERTS:true}
          order_updates: ${HIBERNATE_ORDER_UPDATES:true}
```

### Flyway Migration Configuration

```yaml
spring:
  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: ${FLYWAY_LOCATIONS:classpath:db/migration}
    validate-on-migrate: true
    out-of-order: false
    clean-disabled: true
```

### Logging Configuration

```yaml
logging:
  level:
    root: ${LOG_ROOT:INFO}
    vn.fernirx.tawatch: ${LOG_APP_LEVEL:DEBUG}
    org.springframework.web: ${LOG_SPRING_WEB:DEBUG}
    org.hibernate.SQL: ${LOG_HIBERNATE_SQL:DEBUG}
    org.hibernate.type.descriptor.sql.BasicBinder: ${LOG_HIBERNATE_BIND:TRACE}
    org.flywaydb: ${LOG_FLYWAY:DEBUG}
```

**Note:** For the complete template with comments, see [`application.example.yml`](../../tawatch-starter/src/main/resources/application.example.yml).

---

## Profile Selection

### Docker Environment (Default)

In `docker-compose.yml`, the profile is set via environment variable:

```yaml
environment:
  SPRING_PROFILES_ACTIVE: ${SPRING_PROFILES_ACTIVE:-docker}
```

### Local Development

When running with Maven, specify the profile:

```bash
mvn spring-boot:run -pl tawatch-starter -Dspring-boot.run.profiles=local
```

---

## Database Migration with Flyway

Tawatch Backend uses **Flyway** for database schema version control and migrations. Flyway automatically applies database migrations when the application starts.

### How Flyway Works

1. **Automatic Migrations** - Flyway runs automatically on application startup
2. **Version Control** - Each migration has a version number (e.g., `V1__init_schema.sql`)
3. **One-time Execution** - Each migration runs only once
4. **Checksum Validation** - Flyway validates that applied migrations haven't changed

### Migration Configuration

Configure Flyway behavior through environment variables:

```bash
# Enable/disable Flyway (enabled by default)
# Flyway is always enabled - controlled in application.yml

# Location of migration scripts
FLYWAY_LOCATIONS=classpath:db/migration
```

### Migration Files Location

```
tawatch-starter/src/main/resources/db/migration/
├── V1__init_schema.sql          # Initial database schema
├── V2__add_new_table.sql        # Future migrations...
└── V3__alter_existing_table.sql
```

### Viewing Migration Status

When the application starts, check logs for Flyway migration status:

```
Successfully applied 1 migration to schema `tawatch_db`
```

### Important Notes

- **DDL Auto set to `validate`** - Hibernate will NOT auto-create tables (Flyway handles this)
- **Baseline on Migrate** - Enabled for existing databases
- **Clean Disabled** - Flyway clean command is disabled to prevent accidental data loss
- **Validate on Migrate** - Checksums are validated before applying new migrations

For detailed migration workflow, see [Development Guide](../development/development-guide.md#database-migrations).

---

## Configuration Best Practices

1. **Never commit sensitive data** - Keep credentials in `.env` (ignored by git)
2. **Use example files** - Provide `.env.example` and `application.example.yml` templates
3. **Document all variables** - Keep this guide updated when adding new configuration
4. **Environment-specific settings** - Use profiles for different deployment scenarios
5. **Default values** - Always provide sensible defaults using `${VAR:default}` syntax
6. **Docker-first approach** - Use `.env` for configuration changes in containerized environments

---

## Applying Configuration Changes

After changing `.env`, restart the application:

#### Linux/macOS
```bash
./bin/linux/stop.sh
./bin/linux/start.sh
```

#### Windows
```cmd
bin\win\stop.bat
bin\win\start.bat
```

**Note:** Changing database configuration may require resetting the database:
```bash
docker compose down -v  # ⚠️ This deletes existing data
```

---

## File Locations

| File               | Linux/macOS                             | Windows                                 |
|--------------------|-----------------------------------------|-----------------------------------------|
| Environment        | `./.env`                                | `.\.env`                                |
| Docker Compose     | `./docker-compose.yml`                  | `.\docker-compose.yml`                  |
| Application Config | `./tawatch-starter/src/main/resources/` | `.\tawatch-starter\src\main\resources\` |