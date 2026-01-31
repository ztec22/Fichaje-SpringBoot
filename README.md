# Fichaje-SpringBoot

## Sobre el proyecto
Es un proyecto simple con API RESTful para mostrar el uso de **MongoDB**, la **Arquitectura Hexagonal**,
tests de integración con **TestContainers**, la configuracion del entorno local con **Docker** compose 
y la integración continua CI/CD con **Github Actions**.


## Tecnologías
- **Java 21**
- **Spring Boot 4**
- **MongoDB**
- **JUnit y Mockito**
- **TestContainers** para pruebas de integración.
- **Maven**
- **Lombok y MapStruct**
- **Docker y Docker Compose**
- Documentación con **Swagger / OpenAPI**
- CI/CD con **GitHub Actions**

## Arquitectura Hexagonal

- application
- domain
- infrastructure

## Modelos

ClockInType: Tipos de fichajes de entrada y salida.
```
{
    "id": "string", 
    "description": "string",
    "io": "boolean" // entrada/salida
}
```
## Endpoints

| Metodo | Endpoint                     |
|-------:|------------------------------|
|    GET | `/api/v1/clockin/types/`     |
|   POST | `/api/v1/clockin/types/`     |
|    PUT | `/api/v1/clockin/types/{id}` |
| DELETE | `/api/v1/clockin/types/{id}` |


## Arrancar el proyecto

1. Iniciar mongodb y mongo express
```bash
docker compose up
```

2. Ejecutar el proyecto desde el IDE Intellij

3. Acceder a la siguiente url: http://localhost:8082/swagger-ui/index.html
