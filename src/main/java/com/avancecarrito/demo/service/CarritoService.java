package com.avancecarrito.demo.service;

import com.avancecarrito.demo.modelo.Producto;
import java.util.List;


public interface CarritoService {
    List<Producto> findAllProductos();
    List<Producto> findAllProductosById(int id);
    
}
