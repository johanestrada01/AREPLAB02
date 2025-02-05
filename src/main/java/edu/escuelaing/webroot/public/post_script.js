console.log("El script se está cargando correctamente");

document.querySelector('button').addEventListener('click', async function(event) {
    event.preventDefault(); // Previene el envío automático del formulario

    const nombre = document.getElementById('nombre').value;
    const nota = document.getElementById('nota').value;
    console.log("POST TRY 1");
    // Verifica que los campos no estén vacíos
    if (nombre && nota !== '') {
        console.log("POST TRY");
        try {
            // Asegúrate de usar 'await' con fetch
            const response = await fetch('http://127.0.0.1:35000/insert.app', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ nombre, nota }),
            });

            // Procesa la respuesta del servidor
            if (response.ok) {
                const resultadoDiv = document.getElementById('resultado');
                resultadoDiv.innerHTML = `<p><strong>${nombre}</strong> ha recibido una nota de <strong>${nota}</strong>.</p>`;
            } else {
                alert('Hubo un error al enviar los datos.');
            }
        } catch (error) {
            alert('Error de conexión: ' + error.message);
        }
    } else {
        alert('Por favor, complete todos los campos.');
    }
});
