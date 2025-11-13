# Sistema de Mueblería - Backend

Sistema backend con Spring Boot y MySQL para gestión de mueblería.

## Cómo Ejecutar

### Levantar el proyecto con Docker

```bash
docker-compose up --build
```

La aplicación estará en: `http://localhost:8080`

## Probar las Funcionalidades

Usar Postman para probar los siguientes endpoints:

### Crear Mueble
```
POST http://localhost:8080/api/muebles
Body (JSON):
{
  "nombre": "Silla Moderna",
  "tipo": "Silla",
  "precioBase": 100.0,
  "stock": 50,
  "tamano": "MEDIANO",
  "material": "Madera"
}
```

### Crear Variante
```
POST http://localhost:8080/api/variantes
Body (JSON):
{
  "nombre": "Barniz Premium",
  "descripcion": "Acabado premium",
  "precioAdicional": 50.0
}
```

### Crear Cotización
```
POST http://localhost:8080/api/cotizaciones
Body (JSON):
[
  {
    "muebleId": 1,
    "varianteId": 1,
    "cantidad": 5
  }
]
```

### Confirmar Venta
```
POST http://localhost:8080/api/cotizaciones/1/confirmar
```

## Ejecutar Tests

```bash
cd demo
./mvnw test
```
