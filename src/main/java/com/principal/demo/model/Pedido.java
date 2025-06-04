package com.principal.demo.model;

public class Pedido {
    
private String nombre;
    private double precio;
    private String imagen;
    private double rating;

    public Pedido(String nombre, double precio, String imagen, double rating) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.rating = rating;
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


}
