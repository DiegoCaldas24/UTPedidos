package com.avancecarrito.demo.service;

import java.util.List;

import com.avancecarrito.demo.modelo.Producto;

public interface ProductoService {
    List<Producto> findAllProductos();
    List<Producto> findAllProductosById(int id);
    
}
