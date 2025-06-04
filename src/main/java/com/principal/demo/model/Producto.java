package com.principal.demo.model;

public class Producto {
    private String nombre;
    private double precio;
    private String imagen;
    private double rating;
    private String categoria;

    public Producto(String nombre, double precio, String imagen, double rating, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.rating = rating;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
