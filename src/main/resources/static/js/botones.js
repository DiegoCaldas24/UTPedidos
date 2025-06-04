document.addEventListener('DOMContentLoaded', function () {
    // Selecciona todos los botones de "Agregar" por su clase (puedes cambiarla si usas otra)
    const agregarBtns = document.querySelectorAll('.btn1');
    const pagarBtn = document.querySelector('.btn2');

    agregarBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            alert('¡Producto agregado! (Bueno... todavía no, esta función está en construcción)');
        });
    });

    if (pagarBtn) {
        pagarBtn.addEventListener('click', function () {
            alert('La función de pago estará disponible pronto.');
        });
    }
});