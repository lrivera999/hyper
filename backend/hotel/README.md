# Hotel Reservas API

REST API para la gestión de reservas de hotel, construida con Spring Boot 3.4, Spring Security (JWT) y SQLite.

## Tecnologías

- Java 21
- Spring Boot 3.4.0
- Spring Security (JWT con JJWT 0.12.5)
- Spring Data JPA + Hibernate 6
- SQLite (base de datos embebida)
- Springdoc OpenAPI 2.7.0 (Swagger UI)

## Requisitos

- JDK 21 (`C:\Program Files\OpenJDK\jdk21.0.10_7`)
- Apache Maven 3.9.x (`C:\Program Files\Apache\Maven\apache-maven-3.9.14`)

## Levantar el servidor

```bat
"C:\Program Files\Apache\Maven\apache-maven-3.9.14\bin\mvn.cmd" spring-boot:run
```

El servidor inicia en `http://localhost:8081`.

La base de datos `hotel.db` se crea automáticamente en la raíz del proyecto al primer arranque.

> **Nota:** si cambias la configuración de Hibernate o la estrategia de nombres, borra `hotel.db` antes de reiniciar para que las tablas se regeneren limpias.

## Documentación interactiva (Swagger UI)

```text
http://localhost:8081/swagger-ui.html
```

Desde Swagger puedes probar todos los endpoints. Para los endpoints protegidos haz clic en **Authorize** e ingresa el token con el formato:

```text
Bearer <token>
```

---

## Autenticación

Todos los endpoints excepto `/auth/login` requieren un token JWT en la cabecera:

```text
Authorization: Bearer <token>
```

### Login

```text
POST /auth/login
```

**Body:**

```json
{
  "username": "admin",
  "password": "1234"
}
```

**Respuesta exitosa (200):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Respuesta fallida (401):**

```json
{
  "mensaje": "Credenciales inválidas"
}
```

El token tiene una validez de **1 hora**.

### Uso del token en Postman

1. Llama a `POST /auth/login` y copia el valor de `token`.
2. En cada solicitud protegida agrega la cabecera:
   - Key: `Authorization`
   - Value: `Bearer eyJhbGciOiJIUzI1NiJ9...`

---

## Endpoints

### Clientes — `/clientes`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/clientes` | Listar todos los clientes |
| GET | `/clientes/{id}` | Obtener cliente por ID |
| POST | `/clientes` | Crear nuevo cliente |
| PUT | `/clientes/{id}` | Actualizar cliente |
| DELETE | `/clientes/{id}` | Eliminar cliente |

**Body (POST / PUT):**

```json
{
  "nombres": "Juan",
  "apellidos": "Pérez",
  "edad": 35,
  "estado": "A"
}
```

---

### Habitaciones — `/habitaciones`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/habitaciones` | Listar todas las habitaciones |
| GET | `/habitaciones/{id}` | Obtener habitación por ID |
| GET | `/habitaciones/disponibles` | Habitaciones sin reserva activa vigente |
| POST | `/habitaciones` | Crear nueva habitación |
| PUT | `/habitaciones/{id}` | Actualizar habitación |
| DELETE | `/habitaciones/{id}` | Eliminar habitación |

**Body (POST / PUT):**

```json
{
  "piso": 2,
  "habitacion": 201,
  "menaje": "cama doble, TV, baño privado, aire acondicionado",
  "estado": "A"
}
```

**Respuesta de `/habitaciones/disponibles`:**
Devuelve las habitaciones con `estado = 'A'` que no tienen una reserva activa cuya `fechaFin` sea igual o posterior a hoy.

---

### Horarios — `/horarios`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/horarios` | Listar todos los horarios |
| GET | `/horarios/{id}` | Obtener horario por ID |
| POST | `/horarios` | Crear nuevo horario |
| PUT | `/horarios/{id}` | Actualizar horario |
| DELETE | `/horarios/{id}` | Eliminar horario |

**Body (POST / PUT):**

```json
{
  "idHabitacion": 1,
  "horaIngreso": "14:00",
  "horaSalida": "12:00",
  "estado": "A"
}
```

---

### Precios — `/precios`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/precios` | Listar todos los precios |
| GET | `/precios/{id}` | Obtener precio por ID |
| POST | `/precios` | Crear nuevo precio |
| PUT | `/precios/{id}` | Actualizar precio |
| DELETE | `/precios/{id}` | Eliminar precio |

**Body (POST / PUT):**

```json
{
  "idReserva": 1,
  "idHabitacion": 1,
  "esPromocion": 0,
  "esFeriado": 0,
  "tarifaAplicada": 150,
  "estado": "A"
}
```

> `esPromocion` y `esFeriado`: `1` = sí, `0` = no.

---

### Reservas — `/reservas`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/reservas` | Listar todas las reservas |
| GET | `/reservas/{id}` | Obtener reserva por ID |
| POST | `/reservas` | Crear nueva reserva |
| PUT | `/reservas/{id}` | Actualizar reserva |
| DELETE | `/reservas/{id}` | Eliminar reserva |
| GET | `/reservas/estadisticas/reservas-por-mes` | Cantidad de reservas por mes y año |
| GET | `/reservas/estadisticas/costo-promedio-por-mes` | Promedio de costo por mes y año |

**Body (POST / PUT):**

```json
{
  "numHuesped": 2,
  "fechaInicio": "2026-04-15",
  "fechaFin": "2026-04-20",
  "estado": "A"
}
```

> Formato de fechas: `YYYY-MM-DD`.

**Respuesta de `/reservas/estadisticas/reservas-por-mes`:**

```json
[
  { "anio": "2026", "mes": "04", "cantidad": 5 },
  { "anio": "2026", "mes": "05", "cantidad": 3 }
]
```

**Respuesta de `/reservas/estadisticas/costo-promedio-por-mes`:**

```json
[
  { "anio": "2026", "mes": "04", "promedioCosto": 175.50 },
  { "anio": "2026", "mes": "05", "promedioCosto": 200.00 }
]
```

---

## Códigos de respuesta

| Código | Descripción |
|--------|-------------|
| 200 | OK — consulta o actualización exitosa |
| 201 | Created — recurso creado correctamente |
| 204 | No Content — eliminación exitosa |
| 400 | Bad Request — datos de entrada inválidos |
| 401 | Unauthorized — credenciales incorrectas |
| 403 | Forbidden — token ausente o inválido |
| 404 | Not Found — recurso no encontrado |
| 500 | Internal Server Error — error del servidor |

---

## Estructura del proyecto

```text
src/main/java/com/hyper/hotel/
├── controller/        # Endpoints REST
│   ├── AuthController.java
│   ├── ClienteController.java
│   ├── HabitacionController.java
│   ├── HorarioController.java
│   ├── PrecioController.java
│   └── ReservaController.java
├── service/           # Lógica de negocio
├── repository/        # Acceso a datos (Spring Data JPA)
├── model/             # Entidades JPA
├── dto/               # Objetos de transferencia (estadísticas)
├── security/          # JWT (JwtUtil, JwtFilter, SecurityConfig)
├── config/            # Configuración de Swagger
└── exception/         # Manejo global de excepciones
```

## Ejecutar pruebas

```bat
"C:\Program Files\Apache\Maven\apache-maven-3.9.14\bin\mvn.cmd" test
```

Incluye pruebas unitarias para servicios (`ClienteService`, `HabitacionService`, `ReservaService`) y pruebas de controladores con `@WebMvcTest`.
