# Microservicio: Cliente-Persona

## Descripción General
Este microservicio forma parte de una solución basada en arquitectura de microservicios, desarrollada como ejercicio técnico backend. El servicio se encarga de la gestión de entidades relacionadas a personas y clientes, incluyendo validaciones de integridad y reglas de negocio esenciales como unicidad de identificación y cliente ID.

## Arquitectura
- Este microservicio está diseñado bajo principios de arquitectura limpia.
- Expone una API RESTful desarrollada con Spring Boot.
- Se comunica de forma asíncrona con el microservicio `cuenta-movimiento` a través de Kafka.
- Utiliza PostgreSQL como base de datos relacional.

## ⚖️ Tecnologías Utilizadas
- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Kafka
- Maven
- Docker & Docker Compose
- Swagger / Springdoc OpenAPI

## ⚙️ Ejecución Local
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/dennysgf/cliente-persona.git
   ```
2. Entrar al directorio:
   ```bash
   cd cliente-persona
   ```
3. Ejecutar con Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   O bien, usando Docker Compose:
   ```bash
   docker-compose up --build
   ```

## 🗂️ Estructura del Proyecto
```
cliente-persona/
├── src/
│   └── main/java/com/microservicio/clientepersona/
│       ├── adapter
│       ├── application.service
│       ├── config
│       ├── domain
│       └── ClientePersonaApplication.java
├── resources/
│   ├── application.yml
│   └── data.sql (opcional)
├── Dockerfile
├── pom.xml
```

## Endpoints Principales
- `GET /clientes`
- `POST /clientes`
- `PUT /clientes/{id}`
- `DELETE /clientes/{id}`

## Swagger - Documentación
Una vez en ejecución:
```
http://localhost:8081/swagger-ui.html
```

## ✅ Funcionalidades Implementadas (F1-F6, F8)
| Código | Descripción                                      | Estado |
|--------|------------------------------------------------|--------|
| F1     | CRUD de Cliente y Persona                       | ✅     |
| F2     | Validación y comunicación con cuenta-movimiento | ✅     |
| F3     | Manejo de errores y validaciones                | ✅     |
| F4     | Reporte de estado de cuenta por fechas          | ✅     |
| F5     | Pruebas unitarias                               | ✅     |
| F6     | Pruebas de integración                          | ✅     |
| F8     | Documentación OpenAPI con Swagger               | ✅     |

## Consideraciones
- La comunicación entre microservicios está diseñada para ser asíncrona usando Kafka.
- La seguridad y autenticación no se implementaron por no ser requeridas en el reto.
- La solución está lista para ser escalada y desplegada en servicios cloud (Railway, GCP).

---

    Elaborado por: **Dennys Belduma**

    Propósito: Evaluación técnica backend Java
