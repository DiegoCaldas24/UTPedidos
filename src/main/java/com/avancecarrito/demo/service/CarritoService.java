package com.avancecarrito.demo.service;

import com.avancecarrito.demo.modelo.Productos;
import java.util.List;
import java.util.Optional;

public interface CarritoService {
    List<Productos> findAllProductos();
    List<Productos> findAllProductosById(int id);
    
}
