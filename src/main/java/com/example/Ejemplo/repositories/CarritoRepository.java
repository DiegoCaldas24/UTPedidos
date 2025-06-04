package com.example.Ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.Carrito;
import com.example.Ejemplo.models.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    
    List<Carrito> findByUsuario(Usuario usuario);
}