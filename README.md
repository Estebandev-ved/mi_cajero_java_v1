📦 Tecnologías utilizadas
Java 17
Spring Boot 3.4.4
Maven
🛠️ Requisitos
JDK 17 o superior
Maven 3.8+
Visual Studio Code
🚀 Instalación y ejecución
Clona el repositorio:

git clone https://github.com/tu-usuario/atm.git
cd atm
Compila y construye el proyecto:

./mvnw clean install
Ejecuta la aplicación:

./mvnw spring-boot:run
O puedes ejecutar el .jar generado en target/:

java -jar target/atm-0.0.1-SNAPSHOT.jar
🧾 Estructura del proyecto
atm/
├── src/
│   ├── main/
│   │   ├── java/                 # Código fuente Java
│   │   └── resources/            # Archivos de configuración
├── pom.xml                       # Archivo de configuración Maven
└── mvnw, mvnw.cmd                # Scripts para ejecutar Maven sin instalarlo
🧪 Pruebas
Puedes ejecutar las pruebas automatizadas con:

./mvnw test
📄 Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

MIT License

Copyright (c) 2025

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
...
