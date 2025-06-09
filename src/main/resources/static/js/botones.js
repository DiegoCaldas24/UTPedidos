document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formPago");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        const telefono = form.telefono.value;
        const codigo = form.codigo.value;
        if (telefono === "" || codigo === "") {
            alert("Por favor completa los campos obligatorios.");
            return;
        } manualmente: form.submit();
    });
});
