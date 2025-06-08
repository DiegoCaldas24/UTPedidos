package com.principal.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Carrito;
import com.principal.demo.model.Producto;
import com.principal.demo.repository.CarritoRepository;
import com.principal.demo.repository.ProductoRepository;



@Service
public class CarritoServiceImpl implements CarritoService {

    private static final int ID_USUARIO_FIJO = 1; // Usuario simulado

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> findAllProductosById(int id) {
        return productoRepository.findByCategoriaId(id);
    }

    @Override
    public List<Carrito> obtenerCarritoPorUsuario() {
        // Usar el id fijo directamente, ya que así está adaptado el repositorio y modelo
        return carritoRepository.findByUsuarioId(ID_USUARIO_FIJO);
    }
}
