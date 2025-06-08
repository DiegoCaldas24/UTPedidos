package com.principal.demo.services;
import java.util.List;
import java.util.Optional;

import com.principal.demo.model.Producto;

public interface ProductosService {
    List<Producto> findAllProductos();
    Optional<Producto> findProductoById(Long id);
    Optional<Producto> findProductoPorNombre(String nombre);
    Producto saveUser(Producto producto);
    void deleteUserById(Long id);

}
