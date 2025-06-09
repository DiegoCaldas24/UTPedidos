package com.avancecarrito.demo.modelo;

public class CodigoVerificacion {
    private String telefono;
    private String codigo;

    public CodigoVerificacion(String telefono, String codigo) {
        this.telefono = telefono;
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCodigo() {
        return codigo;
    }
}
