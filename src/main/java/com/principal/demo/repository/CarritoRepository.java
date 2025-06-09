package com.principal.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.Carrito;
import com.principal.demo.model.Producto;
@Repository
public interface CarritoRepository extends JpaRepository<Carrito,Integer>{
    List<Carrito> findByUsuarioId(Integer idUsuario);
}

