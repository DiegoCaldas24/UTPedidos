package com.example.Ejemplo.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Ejemplo.models.Categoria;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.repositories.CategoriaRepository;
import com.example.Ejemplo.repositories.ProductoRepository;

@Service
@Primary
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final Path rootLocation = Paths.get("src/main/resources/static/images/");

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> findRecent() {
        return productoRepository.findAll().stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto save(Producto producto, MultipartFile imagen) {
        // Manejar la imagen si se proporciona
        if (imagen != null && !imagen.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            try {
                Files.copy(imagen.getInputStream(), this.rootLocation.resolve(filename));
                producto.setImagenUrl("/images/" + filename);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
            }
        }
        
        // Guardar el producto
        return productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByCategoriaNombre(String categoriaNombre) {
        Categoria categoria = categoriaRepository.findByNombre(categoriaNombre);
        if (categoria != null) {
            return productoRepository.findByCategoria(categoria);
        }
        return List.of();
    }
}