# Sistema de Muebleria - Backend

- https://github.com/CookieDChan/app_mueble_tarea2

## Descripcion del Proyecto

Sistema para gestionar una muebleria que permite:
- Administrar catalogo de muebles (CRUD completo)
- Gestionar variantes que modifican el precio de los muebles
- Crear cotizaciones con multiples productos
- Confirmar cotizaciones como ventas con validacion de stock automatica

## Tecnologias Utilizadas

- **Java 21**: Lenguaje de programacion
- **Spring Boot 3.5.7**: Framework principal
- **Spring Web**: Para crear API REST
- **Spring Data JPA**: Persistencia de datos
- **MySQL 8.0**: Base de datos relacional
- **JUnit 5**: Framework de testing
- **Mockito**: Mocking para tests unitarios
- **Maven**: Gestor de dependencias
- **Docker**: Contenedorizacion

## Estructura del Proyecto

```
demo/
├── src/main/java/com/example/demo/
│   ├── model/              # Entidades JPA
│   │   ├── Mueble.java
│   │   ├── Variante.java
│   │   ├── Cotizacion.java
│   │   ├── DetalleCotizacion.java
│   │   └── ItemCotizacion.java
│   ├── repository/         # Repositorios JPA
│   │   ├── MuebleRepository.java
│   │   ├── VarianteRepository.java
│   │   ├── CotizacionRepository.java
│   │   └── DetalleCotizacionRepository.java
│   ├── service/            # Logica de negocio
│   │   ├── MuebleService.java
│   │   ├── VarianteService.java
│   │   └── CotizacionService.java
│   ├── controller/         # Controladores REST
│   │   ├── MuebleController.java
│   │   ├── VarianteController.java
│   │   └── CotizacionController.java
│   └── pattern/            # Patrones de diseno
│       ├── PrecioStrategy.java
│       ├── PrecioNormalStrategy.java
│       ├── PrecioConVarianteStrategy.java
│       ├── PrecioConDescuentoStrategy.java
│       ├── CalculadoraPrecio.java
│       └── MuebleBuilder.java
└── src/test/java/          # Tests unitarios
    ├── service/
    │   ├── MuebleServiceTest.java
    │   ├── VarianteServiceTest.java
    │   └── CotizacionServiceTest.java
    └── pattern/
        └── PrecioStrategyTest.java
```

## Patrones de Diseno Implementados

### 1. Strategy (Estrategia)

**Ubicacion**: `demo/src/main/java/com/example/demo/pattern/`

**Problema que resuelve**: Se necesitan diferentes formas de calcular el precio de un mueble dependiendo del contexto (precio normal, con variante, con descuento).

**Implementacion**:
- `PrecioStrategy` - Interfaz que define el contrato
- `PrecioNormalStrategy` - Calcula precio base sin modificaciones
- `PrecioConVarianteStrategy` - Calcula precio base + variante
- `PrecioConDescuentoStrategy` - Aplica descuento porcentual
- `CalculadoraPrecio` - Contexto que usa las estrategias

**Beneficios**:
- Facil agregar nuevas estrategias de precio sin modificar codigo existente
- Permite cambiar la estrategia en tiempo de ejecucion
- Elimina condicionales complejos

### 2. Builder (Constructor)

**Ubicacion**: `demo/src/main/java/com/example/demo/pattern/MuebleBuilder.java`

**Problema que resuelve**: La clase Mueble tiene muchos atributos y crear instancias puede ser complejo y poco legible.

**Implementacion**:
- `MuebleBuilder` - Constructor paso a paso para objetos Mueble

**Beneficios**:
- Codigo mas legible al crear muebles
- Permite construir objetos complejos de forma incremental
- Evita constructores con muchos parametros

## Modelo de Datos

### Mueble
- `id`: Identificador unico
- `nombre`: Nombre del mueble
- `tipo`: Tipo de mueble (silla, mesa, etc.)
- `precioBase`: Precio base sin variantes
- `stock`: Cantidad disponible
- `estado`: ACTIVO o INACTIVO
- `tamano`: GRANDE, MEDIANO, PEQUENO
- `material`: Material del mueble

### Variante
- `id`: Identificador unico
- `nombre`: Nombre de la variante
- `descripcion`: Descripcion detallada
- `precioAdicional`: Precio extra que agrega al mueble

### Cotizacion
- `id`: Identificador unico
- `fecha`: Fecha de creacion
- `estado`: COTIZACION o VENTA
- `total`: Total calculado automaticamente
- `detalles`: Lista de items en la cotizacion

### DetalleCotizacion
- `id`: Identificador unico
- `mueble`: Referencia al mueble
- `variante`: Referencia a la variante (opcional)
- `cantidad`: Cantidad de unidades
- `precioUnitario`: Precio por unidad (calculado)
- `subtotal`: Total del item (calculado)

## Como Ejecutar

### Opcion 1: Con Docker Compose (Recomendado)

```bash
docker-compose up --build
```

La aplicacion estara disponible en: `http://localhost:8080`
MySQL estara disponible en: `localhost:3306`

### Opcion 2: Ejecutar localmente

Requisitos:
- Java 21 instalado
- MySQL 8.0 corriendo en puerto 3306
- Maven instalado

Pasos:
1. Configurar MySQL con usuario `root` y password `root`
2. Crear base de datos `muebleria_db` (o dejar que Spring la cree)
3. Ejecutar:

```bash
cd demo
./mvnw spring-boot:run
```

## API Endpoints

### Muebles

#### Crear mueble
```
POST http://localhost:8080/api/muebles
Content-Type: application/json

{
  "nombre": "Silla Moderna",
  "tipo": "Silla",
  "precioBase": 100.0,
  "stock": 50,
  "tamano": "MEDIANO",
  "material": "Madera"
}
```

#### Listar todos los muebles
```
GET http://localhost:8080/api/muebles
```

#### Listar muebles activos
```
GET http://localhost:8080/api/muebles/activos
```

#### Obtener mueble por ID
```
GET http://localhost:8080/api/muebles/1
```

#### Actualizar mueble
```
PUT http://localhost:8080/api/muebles/1
Content-Type: application/json

{
  "nombre": "Silla Premium",
  "tipo": "Silla",
  "precioBase": 150.0,
  "stock": 30,
  "estado": "ACTIVO",
  "tamano": "GRANDE",
  "material": "Roble"
}
```

#### Desactivar mueble
```
DELETE http://localhost:8080/api/muebles/1
```

### Variantes

#### Crear variante
```
POST http://localhost:8080/api/variantes
Content-Type: application/json

{
  "nombre": "Barniz Premium",
  "descripcion": "Acabado con barniz de alta calidad",
  "precioAdicional": 50.0
}
```

#### Listar todas las variantes
```
GET http://localhost:8080/api/variantes
```

#### Obtener variante por ID
```
GET http://localhost:8080/api/variantes/1
```

### Cotizaciones

#### Crear cotizacion
```
POST http://localhost:8080/api/cotizaciones
Content-Type: application/json

[
  {
    "muebleId": 1,
    "varianteId": 1,
    "cantidad": 5
  },
  {
    "muebleId": 2,
    "varianteId": null,
    "cantidad": 3
  }
]
```

**Nota**: Si no se quiere agregar variante, usar `"varianteId": null`

#### Listar todas las cotizaciones
```
GET http://localhost:8080/api/cotizaciones
```

#### Obtener cotizacion por ID
```
GET http://localhost:8080/api/cotizaciones/1
```

#### Confirmar venta
```
POST http://localhost:8080/api/cotizaciones/1/confirmar
```

**Importante**: No lleva body. El ID va en la URL.

**Validaciones**:
- Verifica que haya stock suficiente para todos los items
- Si no hay stock, retorna error "Stock insuficiente"
- Si la cotizacion ya fue confirmada, retorna error
- Al confirmar, decrementa automaticamente el stock

## Testing

El proyecto incluye 17 tests unitarios que cubren:

### Tests de Servicios
- **MuebleServiceTest** (7 tests): CRUD completo de muebles
- **VarianteServiceTest** (3 tests): Calculo de precios con variantes
- **CotizacionServiceTest** (3 tests): Confirmacion de ventas y validacion de stock

### Tests de Patrones
- **PrecioStrategyTest** (4 tests): Diferentes estrategias de calculo de precio

### Ejecutar tests

```bash
cd demo
./mvnw test
```

### Resultado esperado
```
Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Configuracion

### Base de Datos

Archivo: `demo/src/main/resources/application.properties`

```properties
# Configuracion MySQL
spring.datasource.url=jdbc:mysql://mysql:3306/muebleria_db
spring.datasource.username=root
spring.datasource.password=root

# Configuracion JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Notas**:
- `spring.jpa.hibernate.ddl-auto=update` crea/actualiza las tablas automaticamente
- `spring.jpa.show-sql=true` muestra las queries SQL en consola (util para debugging)

## Dependencias Maven

Las dependencias se gestionan en `pom.xml`:

- **spring-boot-starter-web**: API REST
- **spring-boot-starter-data-jpa**: Persistencia con JPA/Hibernate
- **mysql-connector-j**: Driver MySQL
- **spring-boot-devtools**: Hot reload durante desarrollo
- **spring-boot-starter-test**: JUnit 5, Mockito y otros tools de testing

## Flujo de Trabajo Tipico

1. **Crear muebles** en el catalogo
2. **Crear variantes** opcionales (barniz, cojines, ruedas, etc.)
3. **Crear cotizacion** seleccionando muebles y variantes
4. **Confirmar cotizacion** como venta (valida stock y decrementa automaticamente)

## Manejo de Errores

La API retorna mensajes de error claros:

- `"Mueble no encontrado con id: X"` - El mueble no existe
- `"Stock insuficiente para el mueble: X"` - No hay suficiente stock
- `"Cotizacion no encontrada"` - La cotizacion no existe
- `"La cotizacion ya fue confirmada como venta"` - No se puede confirmar dos veces

## Uso de IA

En el desarrollo de este proyecto se utilizo inteligencia artificial para:

- Recomendar los patrones de diseno (Strategy y Builder) apropiados para resolver los problemas del sistema
- Crear las clases modelo (Mueble, Variante, Cotizacion, DetalleCotizacion)
- Apoyar con la implementacion de las clases de los patrones de diseno
- Redactar este archivo README con documentacion completa del proyecto
