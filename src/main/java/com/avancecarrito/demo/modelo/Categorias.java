package com.avancecarrito.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(nullable = false, length = 30)
    private String nombre;

    // Constructor vacío
    public Categorias() {
    }

    // Constructor completo
    public Categorias(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
