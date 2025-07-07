function abrirModal(button) {
    document.getElementById('modalDetalle').style.display = 'block';
    document.getElementById('modalNombre').innerText = button.dataset.nombre;
    document.getElementById('modalPrecio').innerText = 'S/ ' + button.dataset.precio;
    document.getElementById('nombreProducto').value = button.dataset.nombre;
    document.getElementById('idProducto').value = button.dataset.id
}

function cerrarModal() {
    document.getElementById('modalDetalle').style.display = 'none';
}
