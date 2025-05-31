# Bancobit: Simulador de Transacciones Bancarias

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
    git clone [https://github.com/Estebandev-ved/atm](https://github.com/Estebandev-ved/atm.git)
    ```
2.  **Navega al directorio del proyecto:**
    ```bash
    cd atm
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
atm/
├── src/
│   ├── main/
│   │   ├── java/                 # Código fuente Java de la aplicación
│   │   └── resources/            # Archivos de configuración y propiedades
├── pom.xml                       # Archivo de configuración principal de Maven
└── mvnw, mvnw.cmd                # Scripts para ejecutar Maven sin necesidad de instalarlo globalmente

---

### 🧪 Pruebas

Puedes ejecutar las pruebas automatizadas del proyecto con el siguiente comando:

```bash
./mvnw test
