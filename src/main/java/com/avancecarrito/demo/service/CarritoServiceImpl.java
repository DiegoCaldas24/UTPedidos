package com.avancecarrito.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avancecarrito.demo.modelo.Producto;
import com.avancecarrito.demo.repository.CarritoRepository;
import com.avancecarrito.demo.repository.ProductoRepository;

public class CarritoServiceImpl implements CarritoService{
    private final ProductoRepository productoRepository;


    private final CarritoRepository carritoRepository;
    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> findAllProductosById(int id) {
       
        throw new UnsupportedOperationException();
    }

}
