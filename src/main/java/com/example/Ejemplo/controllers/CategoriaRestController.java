package com.example.Ejemplo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejemplo.models.Categoria;
import com.example.Ejemplo.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }
}