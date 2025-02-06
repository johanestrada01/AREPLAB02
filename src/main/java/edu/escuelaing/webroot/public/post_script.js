console.log("El script se está cargando correctamente");

document.querySelector('button').addEventListener('click', function(event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const nota = document.getElementById('nota').value;

    if (nombre && nota !== '') {
        fetch('http://127.0.0.1:35000/app/insert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre, nota }),
        }).catch(error => {
            console.log("ok");
        });
        setTimeout(() => {
            window.location.href = 'http://127.0.0.1:35000'; 
        }, 100); 
    } else {
        alert('Por favor, complete todos los campos.');
    }
});
