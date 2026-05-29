# Proyecto: Sistema de Metrología

## Descripción
Este proyecto es un sistema de gestión para un laboratorio de metrología. Está construido usando una arquitectura de 10 microservicios independientes que se comunican entre sí. Sirve para registrar laboratorios, clientes, instrumentos, y gestionar el flujo completo de las órdenes de trabajo, calibraciones, certificados y facturas.

## Estudiantes
- Joaquín
- [Tu Nombre]

## Tecnologías utilizadas
- Spring Boot (Java) [cite: 303]
- Maven [cite: 303]
- Spring Data JPA [cite: 303]
- Base de datos: MariaDB mediante XAMPP [cite: 303, 320]
- Flyway (para la creación automática de tablas) [cite: 304]
- WebClient (para la comunicación REST entre los microservicios) [cite: 303]
- Validation y Lombok [cite: 303]

## Estructura de las APIs
El sistema cuenta con 10 microservicios divididos en 4 niveles de dependencia:

- Nivel 1 (Base): laboratorios, clientes, usuarios[cite: 370, 371, 372]. Son independientes y no consumen a otras apis.
- Nivel 2 (Intermedias): instrumentos, patrones[cite: 374, 375]. Consumen a la api de laboratorios mediante WebClient.
- Nivel 3 (Transaccionales): ordenes, calibracion[cite: 379, 380]. Es el núcleo del sistema y cruzan los datos de clientes, instrumentos, patrones y usuarios.
- Nivel 4 (Salida): certificados, facturacion, notificaciones[cite: 382, 384, 385]. Generan los documentos finales consumiendo a ordenes y calibracion.

Todas las APIs tienen un CRUD completo implementado (métodos GET, POST, PUT y DELETE)[cite: 667, 668].

## Pasos para ejecutar el proyecto localmente

1. Configuración de la base de datos:
- Abrir XAMPP e iniciar los servicios de Apache y MySQL[cite: 111].
- Ir a http://localhost/phpmyadmin[cite: 113].
- Crear 10 bases de datos completamente vacías con estos nombres exactos: laboratorios, clientes, usuarios, instrumentos, patrones, ordenes, calibracion, certificados, facturacion, notificaciones[cite: 114, 553]. No es necesario crear las tablas a mano.

2. Ejecución:
- Clonar este repositorio.
- Para que el sistema funcione completo, se deben ejecutar las 10 aplicaciones al mismo tiempo[cite: 572].
- Abrir una terminal en la carpeta de cada microservicio y ejecutar el comando: mvn spring-boot:run[cite: 595].
- Al arrancar, Flyway detectará las bases de datos vacías y creará las tablas de forma automática[cite: 553].

3. Pruebas en Postman:
Como las apis están conectadas por WebClient, los datos se deben probar estrictamente en orden[cite: 554]:
- Paso 1: Crear registros (POST) en laboratorios, clientes y usuarios[cite: 558].
- Paso 2: Crear registros en instrumentos y patrones (asignándoles el ID del laboratorio creado en el paso anterior)[cite: 561].
- Paso 3: Crear una orden de trabajo y luego una calibracion[cite: 563, 565].
- Paso 4: Finalmente, generar un certificado, una factura o probar la notificación[cite: 566, 567, 568].
