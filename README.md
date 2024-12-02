# TRABAJO FINAL SERVIDOR 1º EVALUACIÓN

**Curso:** 2º Desarrollo de Aplicaciones Web (DAW)

**Alumno:** Santiago Pérez


## ÍNDICE

1. [INTRODUCCIÓN](#introducción)
2. [FUNCIONALIDADES DEL PROYECTO Y TECNOLOGÍAS UTILIZADAS](#funcionalidades-del-proyecto-y-tecnologías-utilizadas)
3. [GUÍA DE INSTALACIÓN](#guía-de-instalación)
4. [GUíA DE USO](#guía-de-uso)
5. [ENLACE A FIGMA](#enlace-a-figma)
6. [CONCLUSIÓN](#conclusión)
7. [CONTRIBUCIONES](#contribuciones)
8. [LICENCIAS](#licencias)
9. [CONTACTO](#contacto)

---

## INTRODUCCIÓN


### Descripción del proyecto

El proyecto Portafolio es una API REST creada en Java con Spring Boot que permite gestionar información sobre proyectos, desarrolladores y tecnologías, facilitando la organización de la trayectoria profesional de un desarrollador y ayudando a empleadores a evaluar candidatos de manera estructurada.


### Justificación

Este proyecto se desarrolla como parte del trabajo final del trimestre para consolidar los conocimientos adquiridos sobre diseño y desarrollo de APIs REST. 


### Objetivos

El trabajo consiste en desarrollar una API REST que gestione la información de diferentes proyectos. Dicha API REST deberá poder gestionar al completo las diferentes tablas de la base de datos para que el usuario pueda gestionar su información por medio de peticiones HTTP.


### Motivación

La motivación principal para desarrollar esta API REST radica en la importancia de contar con una herramienta eficiente que permita gestionar y presentar la información relacionada con proyectos de manera estructurada.

---


## FUNCIONALIDADES DEL PROYECTO Y TECNOLOGÍAS UTILIZADAS


### Funcionalidades

- **Gestión de información de proyectos**
- **Gestión de información de desarrolladores**
- **Gestión de información de tecnologías**
- **Paginación**


### Tecnologías utilizadas

- **Visual Studio Code**
- **Spring Boot**
- **Maven**
- **Java**
- **MySQL**
- **Thunder Client**
- **Git**
- **GitHub**

---


## GUÍA DE INSTALACIÓN

Sigue los pasos a continuación para instalar y ejecutar la API REST.

### Requisitos previos

Asegúrate de tener instalados en tu sistema:

- **Java 17**  
  [Descargar Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Maven**  
  [Instrucciones de instalación de Maven](https://maven.apache.org/install.html)
- **MySQL** (o tu motor de base de datos preferido)  
  [Descargar MySQL](https://dev.mysql.com/downloads/)
- **Git**  
  [Descargar Git](https://git-scm.com/)

**Paso 1** Clona el repositorio:

   ```bash
   git clone https://github.com/DiMaPaGa/servidorporfolio.git
   ```

**Paso 2** Navega al directorio del proyecto:

   ```bash
   cd servidorporfolio
   ```

**Paso 3** Instala las dependencias y compila el proyecto con Maven :
 **ATENCION**: Asegurate de tener Maven instalado en tu sistema.
   ```bash
   mvn clean install
   ```
**Paso 4** Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---


## GUÍA DE USO

**Paso 1** Ejecuta el script **schema.sql** en MySQL.

**Paso 2** Accede a http://localhost:8080/swagger-ui.html y prueba los endpoints que quieras.

---


## CONCLUSIÓN

Portfolio API es una herramienta poderosa y flexible que facilita la gestión de información profesional para desarrolladores y empleadores. Este proyecto no solo demuestra el potencial de combinar tecnologías modernas como Java 17, Spring Boot y MySQL, sino que también refleja la importancia de estructurar y documentar aplicaciones robustas.

Esperamos que esta API sirva como una base sólida para proyectos futuros y que inspire a otros a seguir desarrollando herramientas útiles para el ámbito profesional.


---


## CONTRIBUCIONES

Este proyecto fue desarrollado como un trabajo individual, pero está diseñado para facilitar futuras colaboraciones. Si deseas contribuir, haz fork al repositorio, crea una rama nueva, realiza los cambios necesarios y haz un commit. Para terminar dube los cmabios a tu repositorio y abre un pull request detallando tus contribuciones.

---


## LICENCIAS

Este proyecto está licenciado bajo la **GNU General Public License v3.0**.  

Puedes utilizar, modificar y distribuir este proyecto según los términos de esta licencia.  
Para más información, consulta el archivo [LICENSE](./LICENSE) o visita la página oficial de la licencia:  
[https://www.gnu.org/licenses/gpl-3.0.html](https://www.gnu.org/licenses/gpl-3.0.html).

---


## CONTACTO

**Nombre:** Santiago Pérez Olivares
**Correo:** [santiago.perez.olivares.16@gmail.com[](mailto:santiago.perez.olivares.16@gmail.com)]
**LinkedIn:** https://www.linkedin.com/in/santiago-p%C3%A9rez-olivares-06086130a/
