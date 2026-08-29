# 🏥 Hospital Montelíbano

Sistema de gestión hospitalaria desarrollado en Java con Spring Boot y Vaadin, que permite administrar pacientes, habitaciones, facturación e indicaciones médicas a través de vistas diferenciadas según el rol del usuario (administrativo, médico o enfermero).

## 📋 Sobre el proyecto

El sistema centraliza la operación básica de un hospital: registro de pacientes, asignación de habitaciones, seguimiento médico y generación de facturas. Cada tipo de usuario cuenta con su propia vista y permisos, reflejando cómo funcionaría en un entorno hospitalario real donde distintos roles necesitan distinta información.

Este proyecto se desarrolló en equipo como práctica académica de Ingeniería de Sistemas, con énfasis en backend con Java y manejo de arquitectura por roles.

### ✨ Funcionalidades

- 🔐 **Inicio de sesión por rol**: acceso diferenciado para Administrativos, Médicos y Enfermeros(as).
- 🧑‍⚕️ **Registro de pacientes**: ingreso de pacientes con selección de tipo de habitación (Individual o compartida).
- 🛏️ **Gestión de habitaciones**: manejo de habitación Tipo A (individual) y Tipo B (2 camas).
- 💊 **Indicaciones médicas**: los médicos pueden registrar indicaciones para cada paciente.
- 🧾 **Facturación**: generación de facturas según los días de estancia activa del paciente.
- 👥 **Menús diferenciados**: vistas propias para cada tipo de usuario (`menuAdministrativos`, `menuMedicos`, `menuEnfermeros`), incluyendo buscador de pacientes.
- 💾 **Persistencia de sesión**: manejo de sesión de usuario con VaadinSession.

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.2.5** — framework de backend
- **Vaadin 24.3.0** — framework para construir la interfaz web directamente desde Java
- **Maven** — gestión de dependencias y build
- Persistencia de datos en archivos de texto (`.txt`) organizados por tipo de registro

## 🚀 Cómo ejecutarlo

Requiere tener **Java 21** y **Maven** instalados.

```bash
# Desde la carpeta del proyecto (donde está el pom.xml)
mvn spring-boot:run
```

La aplicación queda disponible en:
```
http://localhost:8080
```

## 📌 Estado del proyecto

Proyecto funcional desarrollado como práctica académica. Actualmente en revisión y corrección de bugs identificados tras el desarrollo inicial (ver historial de commits).

## 👥 Equipo del proyecto

Este proyecto fue desarrollado en equipo como práctica académica de Ingeniería de Sistemas.

- **Miguel Ángel Arbeláez** — Ingeniería de Sistemas
- **Diego Ríos** — Ingeniería de Sistemas — [@diegoriosmd](https://github.com/diegoriosmd)
- **Jerónimo Toro Rodríguez** — Ingeniería de Sistemas — [@ToroAdmin4](https://github.com/ToroAdmin4)

## 👤 Autor de este repositorio

**Miguel Ángel Arbeláez Lascarro**
[GitHub](https://github.com/HappyOfVideo) · miguel.arbelaez.lascarro@gmail.com
