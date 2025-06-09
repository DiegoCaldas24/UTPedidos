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
        return productoRepository.findAllById(id);
    }

    @Override
    public List<Carrito> obtenerCarritoPorUsuario(int id) {
        return carritoRepository.findByUsuarioId(id);
    }
}
