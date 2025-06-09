package com.example.Ejemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.DetalleCarrito;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Integer> {
} 