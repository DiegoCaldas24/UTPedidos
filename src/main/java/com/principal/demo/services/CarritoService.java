package com.principal.demo.services;
import java.util.List;

import com.principal.demo.model.Carrito;
import com.principal.demo.model.Producto;


public interface CarritoService {
    List<Producto> findAllProductos();
    List<Producto> findAllProductosById(int id);
    List<Carrito> obtenerCarritoPorUsuario();
    
}
