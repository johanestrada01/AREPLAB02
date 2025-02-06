fetch("http://127.0.0.1:35000/app/get")
    .then(response => response.json())
    .then(data => {
        console.log("Datos recibidos:", data);

        const tableBody = document.querySelector("#gradesTable tbody");
        tableBody.innerHTML = "";

        // Iterar sobre las claves del objeto 'data' y crear filas dinámicamente
        Object.keys(data).forEach(key => {
            const row = document.createElement("tr");

            // Crear celdas con la clave y el valor
            row.innerHTML = `<td>${key}</td><td>${data[key]}</td>`;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error("Error al obtener los datos:", error));
