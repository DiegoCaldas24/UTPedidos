package com.principal.demo.model;

public class Cupon {
    private String nombre;
    private String codigo;
    private String imagen;

    public Cupon(String nombre, String codigo, String imagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
