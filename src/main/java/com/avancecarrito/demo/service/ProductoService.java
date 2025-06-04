package com.avancecarrito.demo.service;

import java.util.List;

import com.avancecarrito.demo.modelo.Productos;

public interface ProductoService {
    List<Productos> findAllProductos();
    List<Productos> findAllProductosById(int id);
    
}
