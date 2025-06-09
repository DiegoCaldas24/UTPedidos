package com.example.Ejemplo.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    private Integer cantidad;
    
    private BigDecimal precio;
    
    private BigDecimal subtotal;
} 