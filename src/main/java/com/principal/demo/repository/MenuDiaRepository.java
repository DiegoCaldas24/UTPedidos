package com.principal.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.MenuDia;
import com.principal.demo.model.Producto;

@Repository
public interface MenuDiaRepository extends JpaRepository<MenuDia, Integer> {

    // Paginación con filtro por categoría
    @Query("SELECT md.producto FROM MenuDia md WHERE md.fecha = CURRENT_DATE AND md.producto.categoria.nombre = :categoria")
Page<Producto> obtenerProductosDelMenuDeHoyPorCategoria(@Param("categoria") String categoria, Pageable pageable);
}
