# Trivia Quest - JavaFX Edition

Proyecto desarrollado para la asignatura **Laboratorio de Programación Orientada a Objetos**.

## Integrantes

- Dylan Atanacio
- Fabricio Morales

## 🚀 Tecnologías utilizadas

- JavaFX para la interfaz gráfica.
- Java 25 LTS (OpenJDK Temurin 25.0.5).
- Maven para la gestión de dependencias.
- MySQL para el almacenamiento de usuarios, preguntas y datos del juego.

## 📁 Estructura del proyecto

- `src/main/java/com/main/Main.java`: clase principal que inicia la aplicación.
- `src/main/resources/`: recursos utilizados por la aplicación, incluyendo los estilos visuales.
- `pom.xml`: configuración de Maven y dependencias.
- `database/trivia_db.sql`: script SQL para crear la base de datos y sus tablas.
- `docs/`: capturas de pantalla del funcionamiento del sistema.

## 🛠️ Requisitos

- JDK 25 LTS.
- MySQL Server (versión compatible con el proyecto).
- Maven.
- Un IDE compatible con Java, como IntelliJ IDEA, Eclipse o Visual Studio Code.

## ▶️ Cómo ejecutar

1. Clonar o descargar el repositorio.
2. Abrir la carpeta del proyecto en el IDE.
3. Configurar la conexión con MySQL.
4. Ejecutar el script SQL ubicado en `database/trivia_db.sql`.
5. Abrir una terminal en la carpeta principal del proyecto.
6. Ejecutar los siguientes comandos:

```bash
mvn clean compile
mvn javafx:run
```

## 🎮 Descripción del proyecto

Trivia Quest es un juego de preguntas y respuestas que permite a los usuarios poner a prueba sus conocimientos mediante diferentes preguntas.

Cuenta con un sistema de usuarios, vidas, puntajes, monedas y comodines que permiten obtener ventajas durante las partidas.

## 📸 Documentación visual

En la carpeta `docs/` se encuentran capturas de pantalla de la aplicación, incluyendo:

- Ventana principal.
- Registro exitoso de un usuario.
- Mensaje de error de excepción.
