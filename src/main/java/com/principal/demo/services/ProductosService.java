package com.principal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.principal.demo.model.Producto;

public interface ProductosService {

    List<Producto> findAllProductos();
    Optional<Producto> findProductoById(Integer id);
    Optional<Producto> findProductoPorNombre(String nombre);


    Producto saveProduct(Producto producto);
    void deleteProductById(Integer id);

    Page<Producto> findAllProductosPaginado(Pageable pageable);

    Page<Producto> obtenerProductosPorCategoriaPaginado(String categoria, Pageable pageable);
    Page<Producto> buscarPorCategoriaYNombre(String categoria, String nombre, Pageable pageable);

}
