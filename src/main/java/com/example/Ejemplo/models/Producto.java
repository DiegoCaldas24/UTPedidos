package com.example.Ejemplo.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    @NotNull
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Categoria categoria;

    @Column(nullable = false, length = 30)
    @NotBlank
    @Size(max = 30)
    private String nombre;

    @Column(nullable = false, precision = 6, scale = 2)
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal precio;

    @Column(nullable = false, length = 255)
    @NotBlank
    @Size(max = 255)
    private String descripcion;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    private Integer stock;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    @NotNull
    private Boolean estado;

    @Column(name = "imagen_url")
    @Size(max = 255)
    private String imagenUrl;

    public Producto() {}

    public Producto(Integer id, Categoria categoria, String nombre, BigDecimal precio, String descripcion, Integer stock, Boolean estado, String imagenUrl) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.estado = estado;
        this.imagenUrl = imagenUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}