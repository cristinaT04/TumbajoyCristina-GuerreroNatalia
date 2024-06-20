document.addEventListener('DOMContentLoaded', () => {
    
    const listPatientsButton = document.getElementById('listPatientsButton');
    const tableBody = document.querySelector('#patientsTable tbody');

    listPatientsButton.addEventListener('click', () => {
        fetchPacientes(tableBody);
    });
});

    async function fetchPacientes(tableBody) {
        try {
            const response = await fetch('http://localhost:8080/pacientes/listar'); 
            if (!response.ok) {
                throw new Error(`Error en la solicitud: ${response.status}`);
            }

            const pacientes = await response.json();
            displayPacientes(pacientes, tableBody);
        } catch (error) {
            console.error('Error al obtener los pacientes');
        }
    }

    function displayPacientes(pacientes, tableBody) {
        tableBody.innerHTML = '';
        pacientes.forEach(patient => {
            const row = document.createElement('tr');

            const id = document.createElement('td');
            id.textContent = patient.id;

            const nombre = document.createElement('td');
            nombre.textContent = patient.nombre;

            const apellido = document.createElement('td');
            apellido.textContent = patient.apellido;

            const dni = document.createElement('td');
            dni.textContent = patient.dni;

            const fechaIngreso = document.createElement('td');
            fechaIngreso.textContent = patient.fechaIngreso;

            const calle = document.createElement('td');
            calle.textContent = patient.domicilioSalidaDto.calle;

            const numero = document.createElement('td');
            numero.textContent = patient.domicilioSalidaDto.numero;

            const localidad = document.createElement('td');
            localidad.textContent = patient.domicilioSalidaDto.localidad;

            const provincia = document.createElement('td');
            provincia.textContent = patient.domicilioSalidaDto.provincia;
            
            row.appendChild(id);
            row.appendChild(nombre);
            row.appendChild(apellido);
            row.appendChild(dni);
            row.appendChild(fechaIngreso);
            row.appendChild(calle);
            row.appendChild(numero);
            row.appendChild(localidad);
            row.appendChild(provincia);

            tableBody.appendChild(row);
        });
    }


