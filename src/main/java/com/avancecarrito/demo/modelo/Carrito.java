package com.avancecarrito.demo.modelo;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrito;
    @OneToOne (mappedBy = "usuarios")
    private Usuarios usuario;
    @OneToMany (mappedBy = "carrito")
    private List<Productos> productos = new ArrayList<>();
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "total")
    private double total;
    
    

    public Carrito(Usuarios usuario, List<Productos> productos, int cantidad, double total) {
        this.usuario = usuario;
        this.productos = productos;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
