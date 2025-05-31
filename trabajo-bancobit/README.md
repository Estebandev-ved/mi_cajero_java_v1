Este proyecto es una aplicación de cajero automático (ATM) desarrollada en Java utilizando el framework Spring Boot. El objetivo es simular operaciones típicas de un cajero bancario como autenticación de usuarios, consulta de saldo, retiros, y posiblemente más funcionalidades bancarias.

## 📦 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **Maven**
- **IDE recomendado**: IntelliJ IDEA

## 🛠️ Requisitos

- JDK 17 o superior
- Maven 3.8+
- IDE como IntelliJ IDEA o Eclipse

## 🚀 Instalación y ejecución

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/atm.git
   cd atm
   ```

2. **Compila y construye el proyecto**:
   ```bash
   ./mvnw clean install
   ```

3. **Ejecuta la aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

   O puedes ejecutar el `.jar` generado en `target/`:
   ```bash
   java -jar target/atm-0.0.1-SNAPSHOT.jar
   ```

## 🧾 Estructura del proyecto

```
atm/
├── src/
│   ├── main/
│   │   ├── java/                 # Código fuente Java
│   │   └── resources/            # Archivos de configuración
├── pom.xml                       # Archivo de configuración Maven
└── mvnw, mvnw.cmd                # Scripts para ejecutar Maven sin instalarlo
```

## 🧪 Pruebas

Puedes ejecutar las pruebas automatizadas con:

```bash
./mvnw test
```

## 📄 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**. Consulta el archivo `LICENSE` para más detalles.

```
MIT License

Copyright (c) 2025

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
...
```

---

Desarrollado por Juan Jose Ortiz con ayuda del profesor DanielDev87
