package com.example.Ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Ejemplo.models.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    @Query("SELECT d.producto, SUM(d.cantidad) as total FROM DetalleVenta d GROUP BY d.producto ORDER BY total DESC")
    List<Object[]> findProductosMasVendidos();
}
