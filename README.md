# Generic Web App - Spring Boot Starter Kit

Una plantilla base (Starter App) robusta, escalable y lista para producción desarrollada en Java y Spring Boot. Este proyecto está diseñado para servir como punto de partida estructurado para aplicaciones web de tres capas, integrando persistencia relacional, seguridad preconfigurada, migraciones de base de datos y un flujo completo de autenticación y registro de usuarios.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Framework Principal:** Spring Boot 3.x
* **Seguridad:** Spring Security 6.x (Autenticación basada en formularios y UserDetailsService)
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Migraciones de BD:** Flyway
* **Motor de Plantillas:** Thymeleaf
* **Gestor de Dependencias:** Gradle
* **Front-End Base:** HTML5, CSS3, JavaScript (Bootstrap / Assets estáticos)

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue un patrón de diseño **Vista-Servidor en Tres Capas**:

```text
src/main/java/com/manrique/Generic_web_app/
├── auth/           # Configuración de Spring Security y UserDetailsService
├── controllers/    # Controladores MVC (Manejo de rutas y vistas)
├── DTOs/           # Objetos de Transferencia de Datos (Validaciones de formularios)
├── entities/       # Entidades JPA (User, Role)
├── exceptions/     # Controladores y manejadores globales de excepciones
├── repository/     # Repositorios Spring Data JPA
└── service/        # Capa de lógica de negocio y transacciones
```
---

## 🔒 Características de Seguridad y Autenticación

* **Cifrado de Contraseñas:** BCryptPasswordEncoder para almacenar hashes seguros en la base de datos.
* **Control de Acceso (RBAC):** Sistema de roles configurable (USER, ADMIN).
* **Protección de Rutas:**
    * Rutas públicas: /, /login, /register, /access-denied, recursos estáticos (/assets/**, /css/**, /js/**).
    * Rutas protegidas: Requieren autenticación explícita (ej. /home).
* **Manejo de Sesiones y Cookies:** Configuración de Logout seguro con invalidación de sesión HTTP y eliminación de cookies JSESSIONID.
* **Manejo de Excepciones HTTP:** Redirección automática a vistas personalizadas para denegación de acceso (403) y errores internos del servidor (500).

---

## 💾 Modelo de Datos y Migraciones (Flyway)

Las migraciones de la base de datos se ejecutan automáticamente al iniciar la aplicación a través de Flyway (classpath:db/migration).

El esquema inicial incluye:
1. **users**: Almacena información de la cuenta, estado activo (is_active) y sellos de tiempo.
2. **roles**: Contiene los roles del sistema.
3. **user_roles**: Tabla intermedia (Relación Muchos a Muchos) con restricción ON DELETE CASCADE.

---

## 🚀 Configuración e Instalación

### 1. Requisitos Previos
* JDK 17 o superior instalado.
* Instancia de PostgreSQL corriendo (localmente o mediante Docker).
* Gradle instalado (o utilizar el wrapper ./gradlew).

### 2. Despliegue de la Base de Datos con Docker (Recomendado)

El proyecto incluye una configuración de Docker Compose (`docker-compose.yml`) con PostgreSQL 16 sobre Alpine Linux para un entorno local rápido y aislado.

Para levantar el contenedor de la base de datos:

docker-compose up -d

Esto iniciará una instancia de PostgreSQL expuesta en el puerto local 5433 con la base de datos `genericWebApp` lista para recibir conexiones de la aplicación.

Si prefieres usar una instancia local de PostgreSQL sin Docker, solo asegúrate de crear la base de datos manualmente y ajustar las variables de entorno correspondientes.

### 3. Variables de Entorno
Para evitar exponer credenciales en el código fuente, la aplicación utiliza variables de entorno en el archivo application.properties:

JDBC_URL=jdbc:postgresql://localhost:5432/nombre_base_datos
JDBC_USER=tu_usuario
JDBC_PASSWORD=tu_contraseña
JDBC_DRIVER=org.postgresql.Driver

Asegúrate de definir estas variables en tu entorno local o en la configuración de ejecución de tu IDE antes de lanzar la aplicación.

### 4. Ejecución del Proyecto

Clonar el repositorio:
git clone https://github.com/tu-usuario/generic-web-app.git
cd generic-web-app

Ejecutar las migraciones y compilar el proyecto:
./gradlew build

Iniciar la aplicación:
./gradlew bootRun

La aplicación estará disponible en http://localhost:8080.

---

## 📝 Usuario Administrador por Defecto

La migración inicial de Flyway crea las tablas e inserta un usuario administrativo de prueba:

* **Username:** admin
* **Password:** admin123
* **Roles:** USER, ADMIN

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.