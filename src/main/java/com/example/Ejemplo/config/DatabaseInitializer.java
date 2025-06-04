package com.example.Ejemplo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Ejemplo.repositories.CategoriaRepository;
import com.example.Ejemplo.repositories.ProductoRepository;
import com.example.Ejemplo.repositories.UsuarioRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            ProductoRepository productoRepository) {
        return args -> {
        };
    }
}