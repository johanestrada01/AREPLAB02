// script.js

document.addEventListener("DOMContentLoaded", () => {
    const button1 = document.getElementById("recurso1");
    const button2 = document.getElementById("recurso2");

    button1.addEventListener("click", () => {
        // Realizar una solicitud GET a la URL local
        fetch('http://127.0.0.1:35000/post.html')
            .then(response => {
                if (response.ok) {
                    return response.text(); 
                }
                throw new Error('Error al obtener el archivo');
            })
            .then(htmlContent => {
                const newPage = document.createElement('html');
                newPage.innerHTML = htmlContent;
                const newHead = newPage.querySelector('head');
                const newBody = newPage.querySelector('body');
                document.head.innerHTML = newHead.innerHTML;
                document.body.innerHTML = newBody.innerHTML;
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
        fetch('http://127.0.0.1:35000/get.html')
            .then(response => {
                if (response.ok) {
                    return response.text(); 
                }
                throw new Error('Error al obtener el archivo');
            })
            .then(htmlContent => {
                const newPage = document.createElement('html');
                newPage.innerHTML = htmlContent;
                const newHead = newPage.querySelector('head');
                const newBody = newPage.querySelector('body');
                document.head.innerHTML = newHead.innerHTML;
                document.body.innerHTML = newBody.innerHTML;
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
});
