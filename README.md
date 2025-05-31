# Cajero Fácil: Simulador de Transacciones Bancarias

Este proyecto es un simulador básico de cajero automático desarrollado en **Java** con **Spring Boot**, diseñado para replicar operaciones bancarias fundamentales como depósitos, retiros y consulta de saldo. Su objetivo es proporcionar una plataforma sencilla para entender la lógica detrás de estas transacciones en un entorno de desarrollo moderno.

### ⚙️ Funcionalidades

* **Depósito de fondos:** Permite añadir dinero a la cuenta del usuario.
* **Retiro de fondos:** Permite sacar dinero de la cuenta, con validación para asegurar que haya saldo suficiente.
* **Consulta de saldo:** Muestra el monto actual disponible en la cuenta.
* **Interfaz de menú interactiva:** Facilita la navegación entre las diferentes operaciones.

---

### 📦 Tecnologías utilizadas

* **Java 17:** El lenguaje de programación principal.
* **Spring Boot 3.4.4:** Framework que facilita la creación de aplicaciones Java autónomas y robustas.
* **Maven:** Herramienta de gestión de proyectos y construcción para Java.

---

### 🛠️ Requisitos

Para ejecutar este proyecto, necesitas tener instalado:

* **JDK 17 o superior**
* **Maven 3.8+**
* **Visual Studio Code** (o tu IDE de preferencia para desarrollo Java)

---

### 🚀 Instalación y ejecución

Sigue estos pasos para poner en marcha el proyecto:

1.  **Clona el repositorio:**
    ```bash
    git clone [https://github.com/Estebandev-ved/cajero123.git](https://github.com/Estebandev-ved/cajero123.git)
    ```
2.  **Navega al directorio del proyecto:**
    ```bash
    cd cajero123
    ```
3.  **Compila y construye el proyecto usando Maven Wrapper:**
    ```bash
    ./mvnw clean install
    ```
4.  **Ejecuta la aplicación:**
    ```bash
    ./mvnw spring-boot:run
    ```
    O puedes ejecutar el archivo `.jar` generado en el directorio `target/` después de la compilación:
    ```bash
    java -jar target/atm-0.0.1-SNAPSHOT.jar
    ```

---

### 🧾 Estructura del proyecto

La estructura principal del proyecto es la siguiente:
...
