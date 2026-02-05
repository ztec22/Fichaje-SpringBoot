# Fichaje-SpringBoot

## Sobre el proyecto
Es un proyecto de gestionar tipos de fichajes con API RESTful para mostrar el uso de **MongoDB**, la **Arquitectura Hexagonal**,
tests de integración con **TestContainers**, la configuracion del entorno local con **Docker** compose, **Kubernetes** 
y la integración continua CI/CD con **Github Actions**.


## Tecnologías
- **Java 21**
- **Spring Boot 4**
- **MongoDB**
- **JUnit y Mockito**
- **TestContainers** para pruebas de integración.
- **Maven**
- **Lombok y MapStruct**
- **Docker, Docker Compose y Kubernetes**
- Documentación con **Swagger / OpenAPI**
- CI/CD con **GitHub Actions**

## Arquitectura Hexagonal

```
 application/
   ├── ports/
   │   ├── input/
   │   └── output/
   ├── services/          
   └── exceptions/

domain/
   ├── model/      
   └── exceptions/
   
infrastructure/
  ├── input/           
  │   └── rest/
  └── output/           
      └── persistence/
```

## Modelos

ClockInType: Tipos de fichajes de entrada y salida.
```
{
    "id": "12342", 
    "description": "Start Work",
    "io": "true" // entrada/salida
}
```
## Endpoints

| Metodo | Endpoint                     |
|-------:|------------------------------|
|    GET | `/api/v1/clockin/types/`     |
|   POST | `/api/v1/clockin/types/`     |
|    PUT | `/api/v1/clockin/types/{id}` |
| DELETE | `/api/v1/clockin/types/{id}` |


## Entorno desarrollo local

1. Iniciar mongodb y mongo express
```bash
docker compose up
```
2. Crear archivo **.env** a partir de env.example

3. Ejecutar el proyecto desde el IDE Intellij

4. Acceder a la siguiente url: http://localhost:8082/swagger-ui/index.html

5. Acceder a mongo express: http://localhost:8081/
    - Usuario y contraseña: express

## Despligue en Kuberbenetes

1. Instalar minikube

2. Crear imagen docker en local o usar la imagen: `ghcr.io/ztec22/fichaje-springboot:1.0`

3. Ejecutar los siguientes commandos

```bash
minikube start
minikube image load ghcr.io/ztec22/fichaje-springboot:1.0

kubectl apply -f springboot-configmap.yml
kubectl apply -f mongo-secrets.yml
kubectl apply -f mongo-script.yml
kubectl apply -f mongo.yml
kubectl apply -f springboot.yml
```

4. Acceder al url del siguiente comando: `minikube service springboot-service --url`
y añadirle la ruta `/swagger-ui/index.html`
5. Al terminar liberar recursos

```bash
kubectl delete -f springboot.yml
kubectl delete -f mongo.yml
kubectl delete -f springboot-configmap.yml
kubectl delete -f mongo-secrets.yml
kubectl delete -f mongo-script.yml

minikube stop
minikube delete --all
```
