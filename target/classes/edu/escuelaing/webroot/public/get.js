fetch("http://127.0.0.1:35000/app/get")
    .then(response => response.json())
    .then(data => {
        console.log("Datos recibidos:", data);

        // Si ya es un objeto, no es necesario hacer parse
        const parsedData = data.response || data;

        const tableBody = document.querySelector("#gradesTable tbody");
        tableBody.innerHTML = "";

        Object.keys(parsedData).forEach(key => {
            const row = document.createElement("tr");
            row.innerHTML = `<td>${key}</td><td>${parsedData[key]}</td>`;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error("Error al obtener los datos:", error));
