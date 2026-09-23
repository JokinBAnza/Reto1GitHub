# Reto 1 — Plataforma de reservas de viviendas (Java + MySQL)

Aplicación de consola para gestionar el alquiler vacacional de una inmobiliaria: usuarios,
viviendas y reservas, con los datos en una base de datos MySQL.

Proyecto académico del **Grado Superior en Desarrollo de Aplicaciones Web (CIFP Plaiaundi)**,
reto de la 2.ª evaluación, **enero–marzo de 2025**. Desarrollado **en equipo**.

## Qué hace

- **Usuarios**: alta y consulta, identificados por DNI.
- **Viviendas**: villas y pisos, agrupados por oficina, con ciudad, dirección, número de
  habitaciones, precio por día y extras (planta, piscina).
- **Reservas**: consulta de viviendas libres entre dos fechas, alta de la reserva con número de
  huéspedes, **cálculo automático del total** a partir del precio por día, y finalización de la
  estancia devolviendo la vivienda a disponible.
- **Oficinas**: gestión de las oficinas y comprobación de que una vivienda pertenece a la oficina
  desde la que se opera.

## Estructura

```
Reto/src/
  clases/        modelo: Vivienda (base), Villa, Piso, Oficina, Usuario, Reserva
  repositorios/  acceso a datos: ConectorBD (JDBC) + Gestion* con las consultas
  view/          menús de consola: Menu, MenuUsuario, MenuVivienda, MenuReservas, MenuOficina
  main/          Main: abre la conexión, lanza el menú y la cierra al salir
```

El acceso a la base de datos usa **`PreparedStatement` con parámetros**, no concatenación de
cadenas.

## Cómo ejecutarlo

1. Levantar **MySQL** y crear la base de datos `MR_ROBOT` con sus tablas (`usuario`, `vivienda`,
   `oficina`, `reserva`).
2. Abrir el proyecto en **Eclipse** y añadir `mysql-connector-j-9.1.0.jar` (incluido en `src/`) al
   *Build Path*.
3. Ajustar la cadena de conexión en `repositorios/ConectorBD.java` si el usuario, la contraseña o
   el puerto no son los del entorno de clase.
4. Ejecutar `main.Main`.

## Ramas

El mismo reto está resuelto varias veces, para comparar diseños:

| Rama | Qué es |
|---|---|
| `master` | Primera entrega |
| `V2`, `V3` | Iteraciones posteriores |
| `VersionInterface` | El modelo reescrito apoyándose en una **interfaz** |
| `VersionAbstract` | El modelo reescrito con una **clase abstracta** — la versión más avanzada |

## Limitaciones conocidas

Es un trabajo de clase y se publica tal cual se entregó:

- Las **credenciales de la base de datos están escritas en el código** (entorno local de clase).
- No hay **tests automatizados** ni script de creación de la base de datos en el repositorio.
- La carpeta `bin/` con los `.class` compilados está versionada.
