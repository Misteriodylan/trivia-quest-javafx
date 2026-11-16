# Registro de uso de Inteligencia Artificial

**Proyecto:** Trivia Quest JavaFX  
**Lenguaje:** Java  
**Tecnologías:** JavaFX, Maven y MySQL  
**Integrantes:** Dylan Atanacio y Fabricio Morales

## 1. Configuración inicial del proyecto JavaFX

**Objetivo:** Configurar el entorno de desarrollo y solucionar problemas relacionados con JavaFX y Maven.

**Prompt reconstruido:**

"Estoy desarrollando un juego de trivia utilizando Java y JavaFX en un proyecto Maven. Necesito ayuda para configurar correctamente las dependencias, el archivo pom.xml y la clase principal para poder ejecutar la aplicación."

**Resultado obtenido:**

Se recibió orientación sobre la configuración de Maven, las dependencias de JavaFX, la estructura de paquetes y la ejecución de la aplicación.

## 2. Conexión con la base de datos

**Objetivo:** Conectar el proyecto Java con MySQL.

**Prompt reconstruido:**

"Estoy desarrollando un juego de trivia en Java y necesito conectarlo a una base de datos MySQL llamada trivia_db. Quiero implementar una clase de conexión utilizando JDBC para poder consultar y guardar información."

**Resultado obtenido:**

Se obtuvo ayuda para configurar la conexión JDBC, establecer la URL de conexión y trabajar con las tablas de usuarios y preguntas.

## 3. Registro e inicio de sesión

**Objetivo:** Implementar el registro y la validación de usuarios.

**Prompt reconstruido:**

"Necesito implementar un sistema de registro e inicio de sesión para mi juego de trivia utilizando JavaFX y MySQL. Quiero que los usuarios puedan registrarse y luego iniciar sesión validando sus datos desde la base de datos."

**Resultado obtenido:**

Se trabajó sobre UsuarioDAO y LoginView para permitir registrar usuarios y validar sus credenciales.

## 4. Implementación del patrón DAO

**Objetivo:** Separar las operaciones de acceso a datos de la lógica del juego.

**Prompt reconstruido:**

"Estoy trabajando con Java y MySQL. Necesito implementar el patrón DAO para separar las consultas SQL de las demás clases del proyecto. Quiero que las operaciones de usuarios y preguntas estén organizadas en sus respectivos DAO."

**Resultado obtenido:**

Se recibió orientación para organizar las clases UsuarioDAO y PreguntaDAO, utilizando JDBC y PreparedStatement.

## 5. Implementación de la capa Service

**Objetivo:** Separar la lógica del negocio de las interfaces y los DAO.

**Prompt reconstruido:**

"Quiero implementar una capa Service en mi proyecto Java. Necesito que las vistas no accedan directamente a los DAO, sino que utilicen los servicios para realizar las operaciones correspondientes. ¿Cómo puedo organizar esta estructura?"

**Resultado obtenido:**

Se trabajó en la creación y utilización de servicios, incluyendo UsuarioService y JuegoService. También se modificó LoginView para utilizar UsuarioService en lugar de acceder directamente a UsuarioDAO.

## 6. Implementación de excepciones personalizadas

**Objetivo:** Incorporar excepciones propias para controlar situaciones del juego.

**Prompt reconstruido:**

"Estoy desarrollando un juego de trivia en Java y necesito implementar excepciones personalizadas. Tengo situaciones como quedarse sin vidas, no tener monedas suficientes o intentar utilizar un comodín que no está disponible. ¿Cómo puedo crear estas excepciones y lanzarlas desde JuegoService?"

**Resultado obtenido:**

Se trabajó con excepciones personalizadas, entre ellas:

- SinVidasDisponiblesException.
- MonedasInsuficientesException.
- CategoriaInvalidaException.
- ComodinNoDisponibleException.

También se revisó que las excepciones se lancen realmente cuando ocurren las situaciones correspondientes.

## 7. Implementación de interfaces

**Objetivo:** Incorporar interfaces para organizar el comportamiento de las clases.

**Prompt reconstruido:**

"Necesito implementar una interfaz en mi proyecto Java de trivia. Quiero que las clases puedan compartir un comportamiento y que ese comportamiento sea utilizado desde la capa Service. ¿Cómo puedo implementar y utilizar una interfaz correctamente?"

**Resultado obtenido:**

Se trabajó con la interfaz Evaluable y su utilización desde JuegoService para evaluar las respuestas de las preguntas.

## 8. Implementación de una jerarquía de herencia

**Objetivo:** Aplicar herencia y polimorfismo en el proyecto.

**Prompt reconstruido:**

"Estoy desarrollando un juego de trivia en Java y necesito implementar una jerarquía de al menos cinco clases, con una clase base y cuatro clases que hereden de ella. Quiero aplicar herencia y polimorfismo y utilizar una interfaz desde la capa Service. ¿Cómo puedo organizarlo?"

**Resultado obtenido:**

Se recibió orientación sobre cómo organizar una jerarquía de clases, reutilizar atributos y métodos mediante herencia y aplicar polimorfismo.

## 9. Funcionamiento de las preguntas y respuestas

**Objetivo:** Revisar la evaluación de las respuestas seleccionadas por el jugador.

**Prompt reconstruido:**

"Estoy teniendo problemas con la evaluación de las respuestas en mi juego de trivia. Necesito revisar el método evaluarRespuesta de la clase Pregunta y comprobar que las opciones seleccionadas coincidan correctamente con la respuesta almacenada."

**Resultado obtenido:**

Se revisó la lógica de evaluación de respuestas y la relación entre las opciones mostradas en pantalla y los datos recuperados desde MySQL.

## 10. Implementación y revisión de comodines

**Objetivo:** Controlar el funcionamiento de los comodines del juego.

**Prompt reconstruido:**

"Estoy desarrollando un juego de trivia que tiene comodines como 50/50, escudo, cambio de pregunta, pista y doble oportunidad. Necesito revisar cómo implementar su funcionamiento desde JuegoService y controlar cuándo pueden utilizarse."

**Resultado obtenido:**

Se recibió ayuda para revisar la lógica de los comodines, sus estados y las condiciones necesarias para utilizarlos.

## 11. Pruebas y depuración del proyecto

**Objetivo:** Detectar y corregir errores durante el desarrollo.

**Prompt reconstruido:**

"Estoy desarrollando un proyecto JavaFX con Maven y MySQL. Voy a compartir mis clases y los mensajes de error que aparecen al ejecutar el programa. Necesito que analices el código, identifiques el problema y me expliques cómo solucionarlo sin modificar partes que ya funcionan."

**Resultado obtenido:**

Se realizaron revisiones de código, análisis de errores de compilación y pruebas del funcionamiento de las distintas funcionalidades.

---

## Consideraciones sobre el uso de IA

La inteligencia artificial se utilizó como herramienta de apoyo durante el desarrollo del proyecto.

Las respuestas obtenidas fueron analizadas y aplicadas al código según las necesidades del sistema. También se realizaron modificaciones, pruebas y correcciones para adaptar las soluciones al funcionamiento de Trivia Quest.

La IA no reemplazó el trabajo de desarrollo ni las decisiones tomadas por los integrantes del grupo.
