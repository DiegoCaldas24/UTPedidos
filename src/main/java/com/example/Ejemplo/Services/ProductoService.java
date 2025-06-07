package com.example.Ejemplo.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Ejemplo.models.Producto;

public interface ProductoService {
    List<Producto> findAll();
    List<Producto> findRecent();
    Producto findById(Integer id);
    Producto save(Producto producto, MultipartFile imagen);
    void delete(Integer id);
    List<Producto> findByCategoriaNombre(String categoriaNombre);
}