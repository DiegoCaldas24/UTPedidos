package com.example.Ejemplo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.Usuario;
import com.example.Ejemplo.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
    List<Venta> findByUsuario(Usuario usuario);
    
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}