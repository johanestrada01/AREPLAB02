// script.js

document.addEventListener("DOMContentLoaded", () => {
    const button1 = document.getElementById("recurso1");
    const button2 = document.getElementById("recurso2");

    button1.addEventListener("click", () => {
        // Realizar una solicitud GET a la URL local
        fetch('http://127.0.0.1:35000/post.html')
            .then(response => {
                if (response.ok) {
                    return response.text();  // Convertir la respuesta en texto (HTML)
                }
                throw new Error('Error al obtener el archivo');
            })
            .then(htmlContent => {
                // Crear un nuevo contenedor de la página
                const newPage = document.createElement('html');
                newPage.innerHTML = htmlContent;

                // Obtener el nuevo head y body
                const newHead = newPage.querySelector('head');
                const newBody = newPage.querySelector('body');

                // Reemplazar el head actual con el nuevo head
                document.head.innerHTML = newHead.innerHTML;

                // Reemplazar el body actual con el nuevo body
                document.body.innerHTML = newBody.innerHTML;

                // Volver a ejecutar los scripts del nuevo contenido
                const scripts = document.querySelectorAll('script');
                scripts.forEach(script => {
                    const newScript = document.createElement('script');
                    newScript.src = script.src;
                    newScript.defer = true;
                    document.body.appendChild(newScript);
                });
            })
            .catch(error => {
                console.error('Hubo un problema con la solicitud:', error);
            });
    });

    button2.addEventListener("click", () => {
        alert("Has hecho clic en el botón 'Recurso 2'");
    });
});
