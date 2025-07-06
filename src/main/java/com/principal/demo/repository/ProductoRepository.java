package com.principal.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.demo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByCategoriaNombre(String nombre);
    Optional<Producto> findProductoById(Integer id);


    @Override
    Page<Producto> findAll(Pageable pageable);
    Page<Producto> findByCategoriaNombre(String nombre, Pageable pageable);
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Page<Producto> findByCategoriaNombreAndNombreContainingIgnoreCase(String categoria, String nombre, Pageable pageable);
}