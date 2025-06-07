package com.example.Ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.Categoria;
import com.example.Ejemplo.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByEstadoTrue();
}