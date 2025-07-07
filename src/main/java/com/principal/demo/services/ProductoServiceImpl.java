package com.principal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Producto;
import com.principal.demo.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productosRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productosRepository = productoRepository;
    }

    @Override
    public List<Producto> findAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Producto> findProductoById(Integer id) {
        return productosRepository.findProductoById(id);
    }

    @Override
    public Optional<Producto> findProductoPorNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public Producto saveProduct(Producto producto) {
        if (producto.getImagenUrl().isEmpty()) {
            producto.setImagenUrl("/imagenes/imagenpordefecto.png");
        }
        return productosRepository.save(producto);
    }

    @Override
    public Page<Producto> findAllProductosPaginado(Pageable pageable) {
        return productosRepository.findAll(pageable);
    }

    @Override
    public Page<Producto> obtenerProductosPorCategoriaPaginado(String categoria, Pageable pageable) {
        return productosRepository.findByCategoriaNombre(categoria, pageable);
    }

    @Override
    public void deleteProductById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserById'");
    }

    @Override
    public Page<Producto> buscarPorCategoriaYNombre(String categoria, String nombre, Pageable pageable) {
        if ((categoria == null || categoria.isEmpty()) && (nombre == null || nombre.isEmpty())) {
            return productosRepository.findAll(pageable);
        } else if (categoria != null && !categoria.isEmpty() && (nombre == null || nombre.isEmpty())) {
            return productosRepository.findByCategoriaNombre(categoria, pageable);
        } else if ((categoria == null || categoria.isEmpty()) && nombre != null && !nombre.isEmpty()) {
            return productosRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        } else {
            return productosRepository.findByCategoriaNombreAndNombreContainingIgnoreCase(categoria, nombre, pageable);
        }
    }

}
