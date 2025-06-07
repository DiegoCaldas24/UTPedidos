package com.example.Ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Ejemplo.models.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, com.example.Ejemplo.models.DetalleVentaId> {
    @Query("SELECT new com.example.Ejemplo.dto.ProductoMasVendidoDTO(p.id, p.nombre, p.precio, p.descripcion, SUM(d.cantidad)) " +
           "FROM DetalleVenta d JOIN d.producto p GROUP BY p.id, p.nombre, p.precio, p.descripcion ORDER BY SUM(d.cantidad) DESC")
    List<?> findProductosMasVendidos();
}
