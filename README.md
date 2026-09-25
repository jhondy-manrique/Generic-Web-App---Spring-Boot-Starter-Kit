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

## 3. Configuración de Variables de Entorno

La aplicación utiliza variables de entorno para gestionar las credenciales y la conexión a la base de datos. Para desarrollo local, puedes definirlas en tu IDE, en un archivo `.env` o exportarlas en tu terminal.

> ⚠️ **Importante:** Las credenciales de la base de datos (`JDBC_USER`, `JDBC_PASSWORD`, `JDBC_DRIVER`,`JDBC_URL`) **deben coincidir exactamente** con las configuradas en el archivo `docker-compose.yml` para asegurar la conexión.

### Variables del Sistema

| Variable        | Descripción | Valor por Defecto / Sugerido        | Estado                                                                                             |
|:----------------| :--- |:------------------------------------|:---------------------------------------------------------------------------------------------------|
| `JDBC_URL`      | URL de conexión JDBC a PostgreSQL | `jdbc:postgresql://localhost:5433/genericWebApp` | **Fijo** (Ajustar solo si cambia el puerto, host o el nombre de la base de datos en el contenedor) |
| `JDBC_DRIVER`   | Driver JDBC de PostgreSQL | `org.postgresql.Driver`             | **Fijo**                                                                                           |
| `JDBC_USER`     | Usuario de la base de datos | `genericUser`                          | Personalizable (Debe coincidir con Docker)                                                         |
| `JDBC_PASSWORD` | Contraseña de la base de datos | `password`                              | Personalizable (Debe coincidir con Docker)                                                         |

---

### Ejemplo de configuración en IntelliJ IDEA
1. Ve al menú superior: `Run` > `Edit Configurations...`
2. Selecciona la configuración de `GenericWebAppApplication`.
3. En el campo **Environment variables**, agrega las variables con el formato:
   `JDBC_URL=jdbc:postgresql://localhost:5433/genericWebApp;JDBC_DRIVER=org.postgresql.Driver;JDBC_USER=genericUser;JDBC_PASSWORD=password`

## 4. Ejecución del Proyecto

**Clonar el repositorio:**
   git clone https://github.com/jhondy-manrique/Generic-Web-App---Spring-Boot-Starter-Kit.git

**Levantar la base de datos:**
   Asegúrate de tener Docker corriendo y ejecuta en la raíz del proyecto:
   docker-compose up -d

**Iniciar la aplicación:**
   Puedes ejecutar la aplicación de cualquiera de las siguientes formas:
  - Desde el IDE (IntelliJ / Eclipse / VS Code): Ejecuta la clase principal GenericWebAppApplication.
  - Desde la terminal:
    ./gradlew bootRun

Una vez iniciada, la aplicación estará disponible en: http://localhost:8080

---

## 📝 Usuario Administrador por Defecto

La migración inicial de Flyway crea las tablas e inserta un usuario administrativo de prueba:

* **Username:** admin
* **Password:** admin123
* **Roles:** USER, ADMIN

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.