package com.avancecarrito.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avancecarrito.demo.modelo.Productos;
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
    public List<Productos> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Productos> findAllProductosById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
