# Servidor Web en Java

## Descripción

Este proyecto implementa un FrameWork web en Java utilizando solo las bibliotecas estándar de Java, sin la necesidad de frameworks como Spring o Spark. El servidor maneja solicitudes HTTP en el puerto 35000 y puede servir diferentes tipos de contenido, incluyendo HTML, imágenes, archivos JavaScript, hojas de estilo CSS y datos en formato JSON. 

Además, se usa como prueba un servidor que tiene la capacidad de recibir y almacenar notas de estudiantes a través de solicitudes POST en formato JSON. Los datos de las notas se guardan en un mapa en memoria, y las solicitudes GET permiten consultar la lista de notas almacenadas.

### Características principales

- Soporte para solicitudes **GET** y **POST**.
- Capacidad para servir **archivos estáticos** (HTML, CSS, JavaScript, imágenes).
- Soporte para **JSON** para almacenar y consultar notas de estudiantes.
- Gestión sencilla de solicitudes en un único hilo (sin concurrencia).


---

## Instalación

### Prerrequisitos
- Java 17
- Git
- Navegador web
- Maven

### Clonación del repositorio
```sh
 git clone https://github.com/johanestrada01/AREPLAB02.git
 cd AREP_LAB01
 mvn clean install
 mvn exec:java
```

### Uso
- El usuario puede extender la clase HttpServer que le va a proporcionar metodos para iniciar un servicio web. El usuario debe crear los metodos REST por medio del metodo get y funciones lambda, y puede iniciar el servicio por el puerto deseado usando el metodo startServer. Hay un metodo que permite indicar al servidor donde encontrar los archivos estáticos.

### Acceso
- Ingresar a 127.0.0.1:35000

## Arquitectura
![Arquitectura](https://www.cablenaranja.com/wp-content/uploads/2021/08/Introduccion-Al-HTML-CableNaranja-1.png)

### Componentes

- Página principal donde podemos encontrar los dos servicios correspondientes.
- Página de envio de datos donde podemos ingrear los datos del estudiante y enviarlos al servidor.
- Página de consulta donde podemos observar los datos suministrados.

### Servicios REST

- http://127.0.0.1:35000/app/data -> Extrae los datos que se encuentran actualmente en el sistema y los retorna en formato JSON.
- http://127.0.0.1:35000/app/insert -> Envia datos al servidor para ser almacenados.
- (Los servicios REST son manejados por medio de funciones lambda que pueden ser inyectados)

## Pruebas

### Automatizadas
- Ejecutar con:
```sh
 mvn test
```
## Tecnologias
- Java
- Maven
- Html
- JavaScript
- CSS

## Autores
- Johan Alejandro Estrada Pastran
