package com.principal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Producto;
import com.principal.demo.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductosService{
    
    private final ProductoRepository productosRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productosRepository = productoRepository;
    }
    
    @Override
    public List<Producto> findAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Producto> findProductoById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findProductoPorNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public Producto saveUser(Producto producto) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

    public List<Producto> obtenerProductosPorCategoria(String categoria){
        return productosRepository.findAllByCategoriaNombre(categoria);
    }
}